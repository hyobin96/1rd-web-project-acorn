<template>
  <div class="modal-backdrop" @click.self="close">
    <div class="modal-content">
      <slot></slot> <!-- 자식 요소를 모달 안에 표시 -->
    </div>
  </div>
</template>

<script setup>
const emit = defineEmits(['close'])
function close() {
  emit('close')
}
</script>

<style scoped>
.modal-backdrop {
  position: fixed;
  inset: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}
.modal-content {
  /* background: white; */
  padding: 20px;
  border-radius: 8px;
}
</style>
