
import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useEventStore = defineStore('event', () => {
    const events = ref([])

    function setEvents(data){
        events.value = data;
    }

    return {
        events,
        setEvents,
    }
})