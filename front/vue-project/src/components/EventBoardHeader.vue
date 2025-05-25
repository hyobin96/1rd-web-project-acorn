<template>
    <div class="event-board-header-container">
        <div class="event-board-header">
            <div class="total-event-count">전체 {{ totalCount }}건</div>
            <ul>
                <li><a>진행중</a></li>
                <li><a>종료</a></li>
            </ul>
        </div>
    </div>
</template>

<script setup>

defineProps({
    totalCount: {
        type: Number,
    }
})

</script>

<style scoped>
.event-board-header-container {
    height: 350px;
    border: 1px solid blue;
    display: flex;
    flex-direction: column;
    justify-content: end;
}

.event-board-header {
    border: 1px solid yellow;
    width: 1200px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
}

.total-event-count {
    font-size: 20px;
    font-weight: 600;
    line-height: 150%;
    letter-spacing: .09px;
}

ul {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
}

ul>li>a {
    font-size: 20px;
    font-weight: 600;
    line-height: 150%;
    margin-left: 10px;
}
</style>