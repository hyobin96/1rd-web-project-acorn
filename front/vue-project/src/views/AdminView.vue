<template>
    <div class="layout">
        <div class="nav">
            <div class="user-management">
                <p>
                    <RouterLink :to="{ name: 'user-management' }">사용자 관리</RouterLink>
                </p>
            </div>
            <div class="event-management">
                <p>
                    <RouterLink :to="{ name: 'event-management' }">이벤트</RouterLink>
                </p>
            </div>
        </div>
        <div class="content">
            <RouterView></RouterView>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
.layout {
    display: flex;
    height: 100vh;
    /* 전체 화면 높이 */
}

.nav {
    width: 300px;
    border: 1px solid wheat;
    display: flex;
    flex-direction: column;
    padding-top: 10px;
}

.nav>div {
    margin-top: 20px;
    margin-left: 20px;
}

.nav>div>p {
    margin: 0;
    font-size: 25px;
}

.content {
    flex: 1;
    padding: 20px;
    overflow: auto;
}

a {
    text-decoration: none;
    color: inherit;
}
</style>
