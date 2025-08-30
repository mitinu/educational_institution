<template>
  <div class="day-container">
    <div class="day-header" v-if="!isCollapsed">
      <div class="date-column">
        <div class="vertical-date" @click.stop="toggleCollapse">
          <span>{{ day.date }}</span>
        </div>
        <div class="lesson-numbers">
          <div class="lesson-zero-number"></div>
          <div v-for="n in 6" :key="n" class="lesson-number">
            <span>{{ n }} пара</span>
          </div>
        </div>
      </div>

      <div class="groups-container">
        <group
          v-for="group in day.groups"
          :key="group.title"
          :group="group"
          class="group-item"
          @emitChange="emitChange"
        />
      </div>
    </div>
    <div class="vertical-date-x" @click.stop="toggleCollapse" v-if="isCollapsed">
      <span>{{ day.date }}</span>
    </div>

  </div>

</template>

<script>
import group from './group.vue'

export default {
  name: 'day',
  components: {
    group
  },
  props: {
    day: {
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
.day-container {
  border: 1px solid #e0e0e0;
  border-radius: 6px;
  margin-bottom: 20px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
}

.day-header {
  display: flex;
}

.date-column {
  display: flex;
  min-width: 100px;
  background: #f9f9f9;
}

.vertical-date {
  writing-mode: vertical-rl;
  transform: rotate(180deg);
  text-align: center;
  padding: 10px 5px;
  font-weight: bold;
  background: #4285f4;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
}
.vertical-date-x {
  text-align: center;
  padding: 10px 5px;
  font-weight: bold;
  background: #4285f4;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
}

.lesson-zero-number{
  height: 40px;
}
.lesson-numbers {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.lesson-number {
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid #eee;
  font-size: 13px;
  color: #555;
}

.lesson-number:last-child {
  border-bottom: none;
}

.groups-container {
  display: flex;
  background: white;

  overflow-x: scroll;
  flex-wrap: nowrap;
}
</style>

