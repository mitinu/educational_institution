<template>
  <div class="group">
    <h6>группы</h6>

    <div class="inputs">
      <input
        type="text"
        placeholder="группа"
        v-model="group"
        class="input-text"
      >
      <input
        type="number"
        placeholder="Курс"
        v-model.number="course"
        min="1"
        max="4"
        class="input-number"
      >
      <button
        @click.stop="addGroup"
        :disabled="!isFormValid"
        class="add-button"
      >
        Добавить
      </button>
    </div>

    <button
      @click.stop="getGroups"
      class="view-all-button"
    >
      Посмотреть все группы
    </button>
    <list-modal
      v-if="visibilityModel"
      title="Список групп"
      :dataTable="groups"
      :columns="columns"
      @closeModal="closeModal"
      @update="getGroups"
      @deleteItem="deleteGroup"
    />
  </div>
</template>
<script>
import listModal from "./listModal.vue";
import funcModal from "../mixins/funcModal.vue";

export default {
  name: 'group',
  mixins: [funcModal],
  components: {listModal},
  data() {
    return {
      group: '',
      course: 1,
      groups: [],
      columns: [
        {
          title: 'ID',
          key: 'id',
          resizable: true,
          fr: "100fr"
        },
        {
          title: 'Название',
          key: 'title',
          resizable: true,
          fr: "100fr"
        },
        {
          title: 'Курс',
          key: 'course',
          resizable: true,
          fr: "100fr"
        },
        {
          title: 'Действия',
          key: 'buttonDelete',
          resizable: false,
          fr: "100fr"
        }
      ]
    }
  },
  computed: {
    isFormValid() {
      return this.group && this.course >= 1 && this.course <= 4;
    }
  },
  methods: {

    getGroups(){
      this.$api.getGroups()
      .then(res => {
        console.log(res)
        this.groups = res.data
        this.viewModel()
      })
      .catch(e => console.error('Ошибка:', e))
    },

    addGroup(){
      if (!this.isFormValid) {
        return;
      }
      console.log("dwawadwad")
      this.$api.addGroup({
        title: this.group,
        course: this.course
      })
      .then(res => {
        console.log('Успех:', res)
        this.getGroups()
      })
      .catch(e => console.error('Ошибка:', e))
    },
    deleteGroup(id){
      this.$api.deleteGroup(id)
        .then(res => {
          console.log('Успех:', res)
          this.getGroups()
        })
        .catch(e => console.error('Ошибка:', e))
    }
  }
}
</script>

<style scoped>
.group {
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

.inputs {
  display: flex;
  gap: 10px;
  margin-bottom: 15px;
}


.input-text,
.input-number {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

.input-text:focus,
.input-number:focus {
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

.view-all-button {
  padding: 10px 15px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  width: 100%;
}

.view-all-button:hover {
  background-color: #218838;
}
</style>
