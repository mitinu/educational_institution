<template>
  <div class="account">
    <h6>учетные записи</h6>

    <div class="inputs">
      <input
          type="text"
          placeholder="полное имя"
          v-model="fullName"
          class="input-text"
      >
      <input
          type="number"
          placeholder="уровень доступа"
          v-model.number="accessLevel"
          min="1"
          max="4"
          class="input-number"
      >
      <button
          @click="addAccount"
          :disabled="!isFormValid"
          class="add-button"
      >
        Добавить
      </button>
    </div>


    <button
        @click.stop="viewModel(), getAccount()"
        class="view-all-button"
    >
      Посмотреть все учетные записи
    </button>
    <list-modal
        v-if="visibilityModel"
        @closeModal="closeModal"
        @refreshData="getAccount"
    />


  </div>
</template>

<script>
import listModal from "./listModal.vue";
import funcModal from "../mixins/funcModal.vue";

export default {
  name: 'Account',
  mixins: [funcModal],
  components: {listModal},
  data() {
    return {
      fullName: '',
      accessLevel: 1,
      account: []
    }
  },
  computed: {
    isFormValid() {
      return this.fullName && this.accessLevel >= 1 && this.accessLevel <= 4;
    }
  },
  methods: {
    getAccount() {
      this.$api.getAccount()
        .then(res => {
          console.log(res)
          this.account = res.data
        })
        .catch(e => console.error('Ошибка:', e))
    },
    addAccount(){
      if (!this.isFormValid) {
        return;
      }
      this.$api.addAccount({
        fullName: this.fullName,
        accessLevel: this.accessLevel
      })
      .then(res => console.log('Успех:', res))
      .catch(e => console.error('Ошибка:', e))
    }

  }
}
</script>

<style scoped>
.account {
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
