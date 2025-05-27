<template>
    <div class="navbar" @click="gotoMain">
        <div class="logo-container"><a class="logo-icon"><img class="logo-img" src="@/assets/logo.png" alt="LOGO" /></a>
        </div>
    </div>
</template>

<script setup>
function gotoMain(){

}
</script>

<style scoped>
.navbar {
    width: 1262.5px;
    height: 215px;
    margin: 0 auto;
    padding-left: 10px;
    /* border: 1px solid darkblue; */
}

.logo-img {
    width: 260px;
    height: 70px;
    display: block;
}
</style>