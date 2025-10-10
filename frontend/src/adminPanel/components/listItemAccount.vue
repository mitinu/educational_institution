<template>
  <ul
    class="groups-list"
    v-for="account in list"
    :key="account.id"
  >
    <li class="group-item">
      <span class="group-info">{{ account.fullName }} ({{ account.accessLevel }} уровень доступа)</span>
      <button class="delete-button" @click="deleteAccount(account.id)">×</button>
    </li>
  </ul>
</template>

<script>
export default {
  name: "listItemGroup",
  props:{
    list:{type: Array, required: true},
  },
  methods:{
    deleteAccount(id){
      this.$emit("deleteItem","account", id)
    }
  }
}
</script>

<style scoped>

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
