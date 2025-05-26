<template>
  <div>
    <div class="input-row">
      <input v-model="playlistStore.link" type="url" placeholder="링크를 붙여넣어주세요" class="input-box" />
    </div>
    <button class="input-btn" @click="playlistStore.savePlaylist">저장</button>
  </div>
</template>

<script setup>
import { usePlaylistStores } from '@/stores/playlist';

const playlistStore = usePlaylistStores()

function logLink() {
  console.log('링크:', playlistStore.link);
  playlistStore.savePlaylist()
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
  /* border-right: none; */
  font-size: 16px;

}

.input-btn {
  background-color: #bfbfbf;
  color: white;
  border: none;
  padding: 0 12px;
  font-size: 20px;
  height: 40px;
  margin: 0 auto;
  display: block;
}

.helper-text {
  font-size: 12px;
  color: #777;
  margin-left: 8px;
  margin-bottom: 16px;
}
</style>
