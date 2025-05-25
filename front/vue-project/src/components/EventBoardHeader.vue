<template>
        <div class="event-board-header">
            <div class="total-event-count">전체 {{totalCount}}건</div>
            <ul>
                <li><a>진행중</a></li>
                <li><a>종료</a></li>
            </ul>
        </div>
</template>

<script setup>

defineProps({
    totalCount:{
        type: Number,
    }
})

</script>

<style scoped>
.event-board-header{
    border: 1px solid yellow;
    height: 200px;
}
</style>