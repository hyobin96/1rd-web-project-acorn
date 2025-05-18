<template>
  <button class="done-btn" @click="submit">완료</button>
</template>

<script setup>
function submit() {
  alert('완료되었습니다!');
}
</script>

<style scoped>
.done-btn {
  width: 100%;
  padding: 10px 0;
  background-color: #d0d0d0;
  color: black;
  font-weight: bold;
  font-size: 14px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
</style>
