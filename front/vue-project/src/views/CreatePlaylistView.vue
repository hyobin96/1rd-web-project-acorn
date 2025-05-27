<template>
  <div class="container">
    <div class="playlist-card">
      <h2 class="title">새 플레이리스트</h2>
      <CreatePlaylistName />
      <CreatePlaylistURL @close = "$emit('close')"/>
    </div>
  </div>
</template>

<script setup>
import CreatePlaylistName from '@/components/CreatePlaylistName.vue'
import CreatePlaylistURL from '@/components/CreatePlaylistURL.vue'
import ExercisePlanForm from '@/components/ExercisePlanForm.vue'
import DoneButton from '@/components/DoneButton.vue'

defineEmits(['close'])
</script>

<style scoped>
.container {
  background-color: #f8f8f8;
  width: 640px;
  height: 340px;
  display: flex;
  justify-content: center;
  align-items: center;
  /* border-radius: 5%; */
}

.playlist-card {
  background-color: white;
  width: 95%;
  height: 90%;
  padding: 30px 24px;
  border-radius: 12px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.05);
}

.title {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}
</style>
