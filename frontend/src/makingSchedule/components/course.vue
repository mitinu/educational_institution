<template>
  <div class="course-container">
    <div class="course-header" @click.stop="toggleCollapse">
      <span class="course-number">{{ course.number }} курс</span>
    </div>

    <div class="days-container" v-if="!isCollapsed">
      <day
        v-for="day in course.days"
        :key="day.date"
        :day="day"
        class="day-item"
        @emitChange="emitChange"
      />
    </div>
  </div>
</template>

<script>
import day from './day.vue'

export default {
  name: 'Course',
  components: {
    day
  },
  props: {
    course: {
      type: Object,
      required: true,
    }
  },
  data() {
    return {
      isCollapsed: false
    }
  },
  methods: {
    toggleCollapse() {
      this.isCollapsed = !this.isCollapsed
    },
    emitChange(){
      this.$emit("emitChange")
    }
  }
}
</script>

<style scoped>
.course-container {
  margin-bottom: 20px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
}

.course-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  background-color: #4285f4;
  color: white;
  cursor: pointer;
  transition: background-color 0.2s;
}

.course-header:hover {
  background-color: #3367d6;
}

.course-number {
  font-weight: 500;
  font-size: 16px;
}
.days-container {
  background-color: #f9f9f9;
  padding: 10px;
}

.day-item {
  margin-bottom: 10px;
}

.day-item:last-child {
  margin-bottom: 0;
}
</style>
