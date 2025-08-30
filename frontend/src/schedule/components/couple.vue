<template>
  <div
    class="couple-card"
    :class="{ 'empty-couple': !Visibility }"
  >
    <div class="couple-number">{{ idx }}</div>
    <div class="couple-content">
      <div class="couple-main-info">
        <span class="subject-title">{{ Visibility ? couple.academicSubject : "Нет пары" }}</span>
        <span class="professor-title">{{ Visibility ? couple.professor : "" }}</span>
      </div>
      <div class="couple-secondary-info">
        <span class="office-title">{{ Visibility ? couple.office : "" }}</span>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'couple',
  props: {
    couple: {
      type: Object,
      required: true
    }
  },
  computed:{
    Visibility() {
      return this.couple.academicSubject!=""&&this.couple.professor!=""&&this.couple.office!=""
    }
  }
}
</script>

<style scoped>

.couple-card {
  display: flex;
  background-color: #f8fafc;
  border-radius: 6px;
  padding: 12px;
  align-items: center;
  transition: background-color 0.2s;
}

.couple-card:hover {
  background-color: #eef2f7;
}

.empty-couple {
  background-color: #f1f5f9;
  color: #94a3b8;
}

.couple-number {
  width: 30px;
  height: 30px;
  background-color: #3498db;
  color: white;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 15px;
  font-weight: bold;
  flex-shrink: 0;
}

.empty-couple .couple-number {
  background-color: #cbd5e1;
}

.couple-content {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.couple-main-info {
  display: flex;
  flex-direction: column;
}

.subject-title {
  font-weight: bold;
  color: #1e293b;
  font-size: 1rem;
}

.empty-couple .subject-title {
  color: #94a3b8;
}

.professor-title {
  color: #64748b;
  font-size: 0.85rem;
}

.couple-secondary-info {
  color: #64748b;
  font-size: 0.85rem;
}
</style>
