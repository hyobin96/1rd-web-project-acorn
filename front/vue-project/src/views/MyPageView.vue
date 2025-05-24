<template>
    <form class="account-container" @submit="handleSubmit">
        <div class="account-img">
            <div class="account">
                <div class="profile-photo-wrap">
                    <img class="profile-photo" :src="imgSrc" alt="프로필 이미지" />
                    <input ref="profileInput" type="file" accept="image/*" style="display: none"
                        @change="handleProfileImageChange" />
                    <span class="nickname-on-photo">y</span>
                    <div class="change-profile-photo" @click="triggerProfileInput">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" width="15" height="15">
                            <path fill="green"
                                d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32l0 144L48 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l144 0 0 144c0 17.7 14.3 32 32 32s32-14.3 32-32l0-144 144 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-144 0 0-144z" />
                        </svg>
                    </div>
                </div>
                <div class="delete-profile-photo">
                    <span class="delete-profile-photo-text" @click="handleDeleteProfilePhoto">
                        이미지 삭제
                    </span>
                </div>
            </div>
            <div class="account-info">
                <div class="nickname">
                    <label>닉네임</label>
                    <input type="text" v-model="nickname" />
                </div>
                <div class="email">
                    <label>이메일</label>
                    <input type="text" v-model="email" readonly />
                </div>
                <div class="gender">
                    <label>성별</label>
                    <div class="gender-options">
                        <label>
                            <input type="radio" name="gender" value="male" v-model="gender" /> 남성
                        </label>
                        <label>
                            <input type="radio" name="gender" value="female" v-model="gender" /> 여성
                        </label>
                        <label>
                            <input type="radio" name="gender" value="none" v-model="gender" /> 선택하지 않음
                        </label>
                    </div>
                </div>
                <div class="birthdate">
                    <label for="birthdate">생년월일</label>
                    <input type="date" id="birthdate" name="birthdate" v-model="birthDate" />
                </div>
                <div class="withdraw">
                    <label>
                        탈퇴하기
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" width="16" height="16">
                            <path
                                d="M310.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-192 192c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L242.7 256 73.4 86.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l192 192z" />
                        </svg>
                    </label>
                </div>
                <button type="submit">완료</button>
            </div>
        </div>
    </form>
</template>

<script setup>
import { ref, onMounted } from "vue";
import api from "@/api/axios";
import defaultImg from "@/assets/default-profile.png"; //기본 프로필 이미지
import { useUserStores } from "@/stores/user";

const store = useUserStores()
const imgSrc = ref(defaultImg);
const nickname = ref("");
const gender = ref("");
const birthDate = ref("");
const profileInput = ref(null);
const email = ref("")

const selectedProfileFile = ref(null);


const BASE_URL = "https://localhost:9443";

async function loadUserInfo() {
    try {
        const res = await api.get("/users/me");
        imgSrc.value = res.data.profileImage
            ? `${BASE_URL}${res.data.profileImage}?t=${Date.now()}`
            : defaultImg;

        nickname.value = res.data.nickname || "";
        email.value = res.data.email || "";
        gender.value = res.data.gender || "";
        birthDate.value = res.data.birthDate || "";
    } catch (err) {
        console.log("에러 메시지:", err.response?.data || err);
    }
}

onMounted(() => {
    loadUserInfo();
});

function triggerProfileInput() {
    console.log("트리거 함수 실행됨", profileInput.value);
    if (profileInput.value) profileInput.value.click();
}

function handleProfileImageChange(event) {
    const file = event.target.files[0];
    if (!file) return;
    imgSrc.value = URL.createObjectURL(file);
    selectedProfileFile.value = file;
}

async function handleSubmit(e) {
    e.preventDefault();
    try {

        if (selectedProfileFile.value) {
            const formData = new FormData();
            formData.append("file", selectedProfileFile.value);

            const res = await api.post("/users/me/profile-image", formData, {
                headers: { "Content-Type": "multipart/form-data" }
            });
            console.log(imgSrc.value);
            store.imgSrc = imgSrc.value
            await loadUserInfo();

        }

        await api.patch("/users/me", {
            gender: gender.value,
            birthDate: birthDate.value
        });
        alert("성별/생년월일 정보 수정 완료~!")
    } catch (err) {
        console.log("에러 메시지: ", err.response.data);
        alert("성별/생년월일 정보 수정 실패~!")
    }
}
</script>

<style scoped>
.account-container {
    width: 500px;
    margin: 120px auto;
}

.account {
    padding-top: 52px;
    text-align: center;
}

.profile-photo-wrap {
    position: relative;
    width: 100px;
    height: 100px;
    margin: 0 auto;
}

.profile-photo {
    width: 100px;
    height: 100px;
    display: block;
    object-fit: cover;
    border-radius: 50%;
}

.nickname-on-photo {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    font-weight: bold;
    font-size: 22px;
    pointer-events: none;
}

.delete-profile-photo {
    margin-top: 10px;
    text-align: center;
}

.delete-profile-photo-text {
    /* color: #888; */
    font-size: 13px;
    cursor: pointer;
    /* text-decoration: underline; */
    transition: color 0.15s;
}

.delete-profile-photo-text:hover {
    color: #f66;
}

.account-info>div {
    width: 400px;
    margin: 0 auto 16px auto;
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.account-info label {
    font-size: 15px;
    font-weight: 400;
    margin-bottom: 4px;
    text-align: left;
}

.account-info input[type="text"],
.account-info input[type="date"] {
    width: 100%;
    height: 44px;
    box-sizing: border-box;
    padding: 0 12px;
    font-size: 15px;
    border: 1px solid #bbb;
    border-radius: 4px;
}

.gender-options {
    display: flex;
    flex-direction: row;
    gap: 18px;
    align-items: center;
}

.gender-options label {
    display: flex;
    align-items: center;
    gap: 4px;
}

button {
    width: 400px;
    height: 44px;
    margin-left: 50px;
    margin-right: 50px;
    border-radius: 4px;
    border: none;
}

/* 프사 변경 */
.change-profile-photo {
    position: absolute;
    width: 25px;
    height: 25px;
    background-color: rgb(227, 226, 226);
    border-radius: 50%;
    right: 0;
    bottom: 0;
}

.change-profile-photo svg {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}
</style>
