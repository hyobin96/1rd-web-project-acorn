<template>
    <div>
        <MemoListItem v-for="memo in store.memoArr" :memo="memo"/>
    </div>
</template>

<script setup>
import MemoListItem from './MemoListItem.vue';
import { useMemoStores } from '@/stores/memo';
const store = useMemoStores()
</script>

<style scoped>

</style>