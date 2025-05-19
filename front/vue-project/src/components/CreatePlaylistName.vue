<template>
  <div class="input-row">
    <input
      v-model="playlistName"
      type="text"
      placeholder="플레이리스트 이름"
      class="input-box"
    />
    <button class="input-btn" @click="logName">입력</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const playlistName = ref('');

function logName() {
  console.log('플레이리스트 이름:', playlistName.value);
}
</script>

<style scoped>
.input-row {
  display: flex;
  margin-bottom: 12px;
}

.input-box {
  flex: 1;
  padding: 8px;
  border: 1px solid #ccc;
  border-right: none;
  font-size: 13px;
}

.input-btn {
  background-color: #bfbfbf;
  color: white;
  border: none;
  padding: 0 12px;
  font-size: 13px;
}
</style>
