<template>
  <div class="settings-container">
    <div class="settings-content">
      <myAlert
        v-model:show="showAlert"
        title="Получить новое расписание"
        message="Вы уверены? Несохраненные изменения пропадут."
        @confirm="handleConfirm"
        @cancel="handleCancel"
      />

      <div class="week-selection">
        <span class="week-label">Выберите неделю:</span>
        <input
          type="week"
          class="week-input"
          @change="selectWeek"
        >
      </div>

      <button
        class="save-button"
        @click.stop="saveSchedule"
        :disabled="!ifChange"
      >
        Сохранить
      </button>
    </div>
  </div>
</template>

<script>
import myAlert from '@/components/myAlert.vue'
import mixinsMyAlert from '@/mixins/mixinsMyAlert.vue'

export default {
  name: 'setting',
  mixins: [mixinsMyAlert],
  components: {
    myAlert
  },
  props: {
    ifChange: {
      type: Boolean,
      required: true,
    }
  },
  data() {
    return{
      weekDate: null
    }
  },
  methods: {
    selectWeek(event) {
      if (!this.ifChange) {
        this.emitWeekDays(event)
      }
      else {
        this.createMyAlert(
          this.emitWeekDays.bind(this, event),
          function (event){
            event.target.value = this.weekDate
          }.bind(this, event)
        )
      }
    },

    emitWeekDays(event) {
      this.$emit('update:ifChange', false);
      this.weekDate = event.target.value
      const [year, weekNum] = event.target.value.split('-W').map(Number);
      const firstDay = this.getFirstDayOfWeek(year, weekNum);
      const weekDays = [];
      for (let i = 0; i < 6; i++) {
        const date = new Date(firstDay);
        date.setDate(firstDay.getDate() + i);
        weekDays.push(this.formattingDate(date));
      }
      this.$emit("selectWeek", weekDays)
    },

    getFirstDayOfWeek(year, week) {
      const date = new Date(year, 0, 1);
      const dayNum = date.getDay() || 7;
      const daysToAdd = 1 - dayNum + (week - 1) * 7;
      date.setDate(date.getDate() + daysToAdd);
      return date;
    },

    formattingDate(date) {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}.${month}.${day}`
    },

    saveSchedule() {
      this.$emit('update:ifChange', false);
      this.$emit('SaveSchedule');
    }
  }
}
</script>

<style scoped>
.settings-container {
  font-family: 'Arial', sans-serif;
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.settings-content {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.week-selection {
  display: flex;
  align-items: center;
  gap: 10px;
}

.week-label {
  font-weight: 500;
  color: #333;
}

.week-input {
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.week-input:focus {
  outline: none;
  border-color: #4a90e2;
  box-shadow: 0 0 0 2px rgba(74, 144, 226, 0.2);
}

.save-button {
  padding: 10px 15px;
  background-color: #4a90e2;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s;
}

.save-button:hover {
  background-color: #3a7bc8;
}

.save-button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.save-button:focus {
  outline: none;
  box-shadow: 0 0 0 2px rgba(74, 144, 226, 0.3);
}
</style>
