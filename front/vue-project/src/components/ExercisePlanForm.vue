<template>
  <div class="goal-block">
    <p class="goal-title">나의 목표</p>
    <input class="goal-desc" placeholder="허리 근력 강화 스트레칭 꾸준히하기"></input>

    <div class="day-buttons">
      <button
        v-for="day in days"
        :key="day.label"
        :class="['day-btn', { selected: day.selected }]"
        @click="toggleDay(day)"
      >
        {{ day.label }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const days = ref([
  { label: '일', selected: false },
  { label: '월', selected: false },
  { label: '화', selected: false },
  { label: '수', selected: false },
  { label: '목', selected: false },
  { label: '금', selected: false },
  { label: '토', selected: false },
]);

function toggleDay(day) {
  day.selected = !day.selected;
}
</script>

<style scoped>
.goal-block {
  margin-bottom: 20px;
}

.goal-title {
  font-weight: bold;
  font-size: 13px;
  margin-bottom: 4px;
}

.goal-desc {
  all: unset;
  font-size: 12px;
  color: #444;
  border-bottom: 1px solid #000;
  padding-bottom: 4px;
  margin-bottom: 12px;
  width: 100%;
}

.day-buttons {
  display: flex;
  justify-content: space-between;
}

.day-btn {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background-color: #e0e0e0;
  font-size: 12px;
  border: none;
  cursor: pointer;
  color: #333;
}

.day-btn.selected {
  background-color: #999;
  color: white;
}
</style>
