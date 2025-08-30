<template>
  <div v-if="show" class="alert-overlay">
    <div class="alert-container">
      <div class="alert-header">
        <h3>{{ title }}</h3>
      </div>
      <div class="alert-body">
        <p>{{ message }}</p>
      </div>
      <div class="alert-footer">
        <button class="cancel-btn" @click.stop="cancel">{{ cancelText }}</button>
        <button class="confirm-btn" @click.stop="confirm">{{ confirmText }}</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "myAlert",
  props: {
    show: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: 'Подтверждение'
    },
    message: {
      type: String,
      default: 'Вы уверены, что хотите выполнить это действие?'
    },
    confirmText: {
      type: String,
      default: 'Подтвердить'
    },
    cancelText: {
      type: String,
      default: 'Отмена'
    }
  },
  methods: {
    confirm() {
      this.$emit('confirm');
      this.$emit('update:show', false);
    },
    cancel() {
      this.$emit('cancel');
      this.$emit('update:show', false);
    }
  }
};
</script>

<style scoped>
.alert-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.alert-container {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  width: 400px;
  max-width: 90%;
  overflow: hidden;
}

.alert-header {
  padding: 16px 24px;
  border-bottom: 1px solid #f0f0f0;
}

.alert-header h3 {
  margin: 0;
  color: #333;
  font-size: 18px;
  font-weight: 600;
}

.alert-body {
  padding: 24px;
  color: #555;
  line-height: 1.5;
}

.alert-footer {
  padding: 16px 24px;
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  border-top: 1px solid #f0f0f0;
}

button {
  padding: 8px 16px;
  border-radius: 4px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
  border: none;
}

.cancel-btn {
  background-color: #f0f0f0;
  color: #555;
}

.cancel-btn:hover {
  background-color: #e0e0e0;
}

.confirm-btn {
  background-color: #4285f4;
  color: white;
}

.confirm-btn:hover {
  background-color: #3367d6;
}
</style>
