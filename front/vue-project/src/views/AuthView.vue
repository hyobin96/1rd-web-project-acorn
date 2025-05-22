<template>
    <div class="d-flex min-vh-100 align-items-center">
        <div class="container text-center p-4 border rounded shadow" style="max-width: 400px;">
            <RouterLink :to="{ name: 'login' }" />
            <RouterView />
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

li {
    list-style: none;
    padding: 0;
    margin: 0;
}

.login {
    width: 458px;
    border: 1px solid black;
}
</style>