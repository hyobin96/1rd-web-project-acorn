<template>
  <div class="input-row">
    <input
      v-model="playlistStore.title"
      type="text"
      placeholder="플레이리스트 이름"
      class="input-box"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { usePlaylistStores } from '@/stores/playlist';

const playlistStore = usePlaylistStores()

function logName() {
  console.log('플레이리스트 이름:', playlistStore.value);
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
  /* border-right: none; */
  font-size: 16px;
}

/* .input-btn {
  background-color: #bfbfbf;
  color: white;
  border: none;
  padding: 0 12px;
  font-size: 13px;
} */
</style>
