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
      <h3>{{title}}</h3>
      <button class="close-button" @click.stop="closeModal">×</button>
    </div>

    <div class="modal-body">

      <myTable
        :columns="columns"
        :dataTable="dataTable"
        @deleteItem="deleteItem"
      >
      </myTable>
      <button class="update-button" @click.stop="update">Обновить</button>
    </div>
  </div>
</template>

<script>
import moveDiv from "@/mixins/moveDiv.vue";
import myTable from "./myTable.vue";
import listItemAccount from "./listItemAccount.vue";

export default {
  name: 'listModal',
  mixins: [moveDiv],
  components: {
    myTable,
    listItemAccount
  },
  props: {
    title: {type: String, default: "Список"},
    dataTable: {type: Array, default: []},
    columns: {type: Array, default: []}
  },
  methods: {
    clickModal(event) {
      if (event.currentTarget.style.zIndex<this.$store.state.zIndexMax){
        event.currentTarget.style.zIndex=this.$store.state.zIndexMax+1
      }
    },
    closeModal() {
      this.$emit('closeModal');
    },
    deleteItem(id){
      this.$emit("deleteItem", id)

    },
    update() {
      this.$emit("update")
    }
  },

}
</script>

<style scoped>
.modal-content {
  display: grid;
  grid-template-rows: 15% 85%;
  position: fixed;
  background: white;
  border-radius: 8px;
  width: 500px;
  max-width: 90%;
  max-height: 80%;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  z-index: 9;
  min-height: 200px;
  min-width: min-content;
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
  display: flex;
  flex-direction: column;
  min-height: 0; /* Важно для корректного скролла */
}

.update-button {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.update-button:hover {
  background-color: #0056b3;
}

</style>
