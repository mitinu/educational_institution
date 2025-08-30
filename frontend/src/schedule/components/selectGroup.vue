<template>
  <div class="select-group-container">
    <!-- Вариант с карточками групп -->
    <div
      class="group-selector"
      v-if="!selectGroupId"
    >
      <h3 class="group-selector__title">Выберите группу</h3>
      <div class="group-list">
        <div
          v-for="group in groups"
          :key="group.id"
          class="group-item"
          @click.stop="selectGroup(group)"
        >
          <span class="group-item__name">{{ group.title }}</span>
        </div>
      </div>
    </div>

    <!-- Вариант с select -->
    <div
      class="select-wrapper"
      v-if="selectGroupId"
    >
      <select
        class="group-select"
        @change="selectGroup2"
        :value = "selectGroupId"
      >
        <option
          v-for="group in groups"
          :key="group.id"
          :value="group.id"
        >
          {{ group.title }}
        </option>
      </select>
      <div class="select-arrow">▼</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SelectGroup',
  props: {
    groups: {
      type: Array,
      required: true
    },
    selectGroupId: {
      type: Number,
      required: true
    },
  },
  methods: {
    selectGroup(group) {
      this.$emit('selectGroup', group.id)
    },
    selectGroup2(event) {
      this.$emit('selectGroup', event.target.value)
    }
  }
}
</script>

<style scoped>
.select-group-container {
  font-family: 'Arial', sans-serif;
  max-width: 600px;
  margin: 0 auto;
}

/* Стили для варианта с карточками */
.group-selector {
  padding: 25px;
  border-radius: 12px;
  background-color: #f8fafc;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

.group-selector__title {
  color: #1e293b;
  margin-bottom: 20px;
  text-align: center;
  font-size: 1.5rem;
  font-weight: 600;
}

.group-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 15px;
}

.group-item {
  padding: 16px;
  background-color: white;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.25s ease;
  border: 1px solid #e2e8f0;
  display: flex;
  flex-direction: column;
}

.group-item:hover {
  background-color: #f1f5f9;
  border-color: #cbd5e1;
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

.group-item__name {
  font-size: 1.1rem;
  color: #1e293b;
  font-weight: 500;
  margin-bottom: 4px;
}

/* Стили для select варианта */
.select-wrapper {
  position: relative;
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
}

.group-select {
  width: 100%;
  padding: 12px 16px;
  padding-right: 40px;
  border: 1px solid #cbd5e1;
  border-radius: 8px;
  background-color: white;
  font-size: 1rem;
  color: #1e293b;
  appearance: none;
  cursor: pointer;
  transition: all 0.2s ease;
}

.group-select:focus {
  outline: none;
  border-color: #60a5fa;
  box-shadow: 0 0 0 3px rgba(96, 165, 250, 0.2);
}

.select-arrow {
  position: absolute;
  top: 50%;
  right: 15px;
  transform: translateY(-50%);
  pointer-events: none;
  color: #64748b;
}

/* Адаптивность */
@media (max-width: 600px) {
  .group-list {
    grid-template-columns: 1fr;
  }

  .group-selector {
    padding: 15px;
  }
}
</style>
