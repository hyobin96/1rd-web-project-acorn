<template>
    <div>
        <div class="nav">
            <p>사용자 관리</p>
            <p>
                <RouterLink :to="{ name: 'event-management' }">이벤트</RouterLink></p>
                
            <p>쇼핑</p>
        </div>
        <div class="content"></div>
        <RouterView></RouterView>
    </div>
</template>

<script setup>

</script>

<style scoped></style>