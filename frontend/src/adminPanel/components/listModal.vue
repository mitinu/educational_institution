<template>
  <div
    class="modal-content"
    :style="{transform: `translate(${positionX}px, ${positionY}px)`,}"
    @mousedown.stop="clickModal"
  >
    <div
      class="modal-header"
      @mousedown="startDrag"
      @touchstart="startDrag"

    >
      <h3>Список групп</h3>
      <button class="close-button" @click.stop="closeModal">×</button>
    </div>

    <div class="modal-body">
      <button class="refresh-button" @click="refreshData">Обновить</button>

      <ul class="groups-list">
        <li v-for="group in groups" :key="group.id" class="group-item">
          <span class="group-info">{{ group.title }} ({{ group.course }} курс)</span>
          <button class="delete-button" @click="deleteGroup(group.id)">×</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import moveDiv from "@/mixins/moveDiv.vue";

export default {
  name: 'listModal',
  mixins: [moveDiv],
  props: {
    value: {type: Array,default: () => []},
  },
  methods: {
    clickModal(event) {
      if (event.currentTarget.style.zIndex<this.$store.state.zIndexMax){
        event.currentTarget.style.zIndex=++this.$store.state.zIndexMax
      }
    },
    closeModal() {
      this.$emit('closeModal');
    },
    refreshData() {
      this.$emit('refreshData');
    },

    deleteGroup(groupId) {
      // Логика удаления группы
      console.log('Удаление группы:', groupId);
      // Здесь должен быть API запрос для удаления группы
      // this.$api.deleteGroup(groupId).then(() => this.refreshGroups());
    },

    loadGroups() {
      // Пример загрузки групп
      // this.$api.getGroups().then(groups => this.groups = groups);
    }
  },

}
</script>

<style scoped>

.modal-content {
  position: fixed;
  background: white;
  border-radius: 8px;
  width: 500px;
  max-width: 90%;
  max-height: 80%;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  z-index: 9;

  resize: auto;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  border-bottom: 1px solid #eee;
  background-color: #f9f9f9;
  cursor: grab;
  &:active{
    cursor: grabbing;
  }
}

.modal-header h3 {
  margin: 0;
  color: #333;
  font-size: 18px;
}

.close-button {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
  color: #666;
  padding: 0;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.close-button:hover {
  color: #333;
  background-color: #eee;
  border-radius: 50%;
}

.modal-body {
  padding: 20px;
}

.refresh-button {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-bottom: 20px;
}

.refresh-button:hover {
  background-color: #0056b3;
}

.groups-list {
  list-style: none;
  padding: 0;
  margin: 0;
  max-height: 400px;
  overflow-y: auto;
}

.group-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  border: 1px solid #eee;
  border-radius: 4px;
  margin-bottom: 8px;
  background-color: #fafafa;
}

.group-item:hover {
  background-color: #f0f0f0;
}

.group-info {
  font-size: 14px;
  color: #333;
}

.delete-button {
  background: none;
  border: none;
  color: #dc3545;
  font-size: 18px;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
}

.delete-button:hover {
  background-color: #dc3545;
  color: white;
}
</style>
