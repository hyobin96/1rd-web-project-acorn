<template>
  <div>
    <div class="input-row">
      <input
        v-model="link"
        type="url"
        placeholder="링크를 붙여넣어주세요"
        class="input-box"
      />
      <button class="input-btn" @click="logLink">저장</button>
    </div>
    <div class="helper-text">> 더 쉽게 저장하기</div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const link = ref('');

function logLink() {
  console.log('링크:', link.value);
}
</script>

<style scoped>
.input-row {
  display: flex;
  margin-bottom: 8px;
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

.helper-text {
  font-size: 12px;
  color: #777;
  margin-left: 8px;
  margin-bottom: 16px;
}
</style>
