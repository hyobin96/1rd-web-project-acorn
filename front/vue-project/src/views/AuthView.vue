<template>
    <div class="login">
        <RouterLink :to="{name: 'login'}" />
        <RouterView />

        <ul class="find_wrap">
            <li><a href="">회원가입</a></li>
            <li><a href="">비밀번호 재설정</a></li>
        </ul>
        <div class="btn_login_wrap">
            <button type="submit"><span class="btn_text">로그인</span></button>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
ul{
     list-style: none;
        padding: 0;
        margin: 0;
}
li{
     list-style: none;
        padding: 0;
        margin: 0;
}
.login{
    width: 458px;
    border: 1px solid black;
}
</style>