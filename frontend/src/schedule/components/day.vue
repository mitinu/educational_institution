<!-- day.vue -->
<template>
  <div class="day-container">
    <h3 class="day-date">{{ date }}</h3>
    <couple
      v-for="idx in [0,1,2,3,4,5]"
      :key="idx"
      :couple="couples[idx]"
    />
  </div>
</template>

<script>
import couple from "./couple.vue";

export default {
  name: 'day',
  components: {
    couple
  },
  props: {
    couples: {
      type: Object,
      required: true
    },
    date: {
      type: String,
      required: true
    },
  },

  created() {
    console.log(this.couples)
  }
}
</script>

<style scoped>

.day-date {
  color: #3498db;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
  font-size: 1.2rem;
}


.day-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

</style>
