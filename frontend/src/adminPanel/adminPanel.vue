<template>
  <div class="admin-panel">
    <h6>Добавить группу</h6>

    <div class="input-group">
      <input
        type="text"
        placeholder="группа"
        v-model="group"
        class="group-input"
      >
      <input
        type="number"
        placeholder="Курс"
        v-model.number="course"
        min="1"
        max="4"
        class="course-input"
      >
      <button
        @click="addGroup"
        :disabled="isFormValid"
        class="add-button"
      >
        Добавить
      </button>
    </div>



  </div>
</template>

<script>
export default {
  name: 'AdminPanel',
  data() {
    return {
      group: '',
      course: 1,
    }
  },
  computed: {
    isFormValid() {
      return !(this.group && this.course >= 1 && this.course <= 4);
    }
  },
  methods: {
    async addGroup() {
      if (!this.group) {
        return;
      }
      fetch('/api/addGroup', {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          title: this.group,
          course: this.course
        })
      })
      .then(response => {
        console.log('Успех:', response);
      })
    }
  }
}
</script>

<style scoped>
.admin-panel {
  max-width: 400px;
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

h6 {
  margin: 0 0 15px 0;
  color: #333;
  font-size: 18px;
}

.input-group {
  display: flex;
  gap: 10px;
  margin-bottom: 15px;
}


.group-input,
.course-input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

.group-input:focus,
.course-input:focus {
  outline: none;
  border-color: #007bff;
}


.add-button {
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.add-button:hover:not(:disabled) {
  background-color: #0056b3;
}

.add-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
