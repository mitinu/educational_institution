<!-- visualization.vue -->
<template>
  <div class="visualization-container">
    <div
      v-for="(schedule, idx) in schedules"
      :key="idx"
      class="day-card"
    >
      <day
        :couples="schedule.couples"
        :date="schedule.date"
        class="day-content"
      />
    </div>
  </div>
</template>

<script>
import day from './day.vue'

export default {
  name: 'visualization',
  components: {
    day
  },
  props: {
    schedules: {
      type: Object,
      required: true
    }
  },
  created() {
    console.log(this.schedules)
  }
}
</script>

<style scoped>
.visualization-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 20px;
}

.day-card {
  background-color: white;
  border-radius: 8px;
  padding: 15px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.day-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}


.day-content {
  margin-top: 10px;
}
</style>
