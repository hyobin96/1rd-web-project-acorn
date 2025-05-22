<template>
    <form class="account-container">
        <div class="account-img">
            <div class="account">
                <div class="profile-photo-wrap">
                    <img class="profile-photo" :src="imgSrc" alt="프로필 이미지" />
                    <span class="nickname-on-photo">y</span>
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
                    <input type="text" />
                </div>
                <div class="email">
                    <label>이메일</label>
                    <input type="text" value="test@email.com" readonly />
                </div>
                <div class="gender">
                    <label>성별</label>
                    <div class="gender-options">
                        <label>
                            <input type="radio" name="gender" value="male" /> 남성
                        </label>
                        <label>
                            <input type="radio" name="gender" value="female" /> 여성
                        </label>
                        <label>
                            <input type="radio" name="gender" value="none" /> 선택하지 않음
                        </label>
                    </div>
                </div>
                <div class="birthday">
                    <label for="birthday">생년월일</label>
                    <input type="date" id="birthday" name="birthday" />
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
import { ref } from "vue";
const defaultImg = "기본이미지url"; // 실제 기본 이미지 경로로 변경
const imgSrc = ref(
    "https://i.namu.wiki/i/Bge3xnYd4kRe_IKbm2uqxlhQJij2SngwNssjpjaOyOqoRhQlNwLrR2ZiK-JWJ2b99RGcSxDaZ2UCI7fiv4IDDQ.webp"
);

function handleDeleteProfilePhoto() {
    imgSrc.value = defaultImg;
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

button{
    width: 400px;
    height: 44px;
    margin-left: 50px;
    margin-right: 50px;
    border-radius: 4px;
    border: none;
}
</style>
