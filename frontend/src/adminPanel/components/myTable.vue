<template>
  <div class="table-container">
    <div
      ref="tableGrid"
      class="table"
      :style="{
        gridTemplateColumns
      }"
    >
      <div
        class="cell cell-head"
        v-for="(column, index) in columns"
        :key="'header-' + index"
      >
        <div class="header-content">
          <span class="header-text">{{ column.title }}</span>
          <div
            v-if="column.resizable"
            class="resize-handle"
            @mousedown="startResize(index, $event)"
          ></div>
        </div>
      </div>

      <template
        v-for="(row, rowIndex) in dataTable"
        :key="'row-' + rowIndex"
      >
        <div
          class="cell cell-body"
          v-for="(column, colIndex) in columns"
          :key="'cell-' + rowIndex + '-' + colIndex"
        >

          <div v-if="column.key=='buttonDelete'" class="cell-content">
            <button class="delete-btn" @click.stop="deleteItem(row.id)">-</button>
          </div>
          <div v-else class="cell-content">{{ row[column.key] }}</div>
        </div>
      </template>
    </div>
  </div>
</template>

<script>

export default {
  name: 'myTable',
  props: {
    columns: { type: Array, default: () => [] },
    dataTable: { type: Array, default: () => [] }
  },
  data(){
    return {
      minFr: 70
    }
  },
  computed: {
    gridTemplateColumns() {
      let gridTemplateColumns = ""
      for (const column of this.columns){
        gridTemplateColumns += column.fr+ " "
      }
      return gridTemplateColumns
    },
    gridWidthFr(){
      let sum = 0
      for (const column of this.columns){
        sum += parseInt(column.fr+ " ")
      }
      return sum
    }
  },
  methods: {
    deleteItem(id){
      this.$emit('deleteItem', id)
    },
    positiveUpdateColumn(columnsFr, index, changeFr) {
      let newFr = columnsFr[index] + changeFr;
      const remainsFr = newFr - this.minFr
      if (remainsFr<0){
        newFr = this.minFr
        if (this.columns[index+1])
          this.positiveUpdateColumn(columnsFr, index+1, remainsFr)
      }
      this.columns[index].fr = `${newFr}fr`;
    },
    negativeUpdateColumn(columnsFr, index, changeFr){
      let newFr = columnsFr[index] + changeFr;
      const remainsFr = newFr - this.minFr
      if (remainsFr<0){
        newFr = this.minFr
        if (this.columns[index-1])
          this.positiveUpdateColumn(columnsFr, index-1, remainsFr)
      }
      this.columns[index].fr = `${newFr}fr`;
    } ,
    startResize(index, event) {
      event.preventDefault();
      const startX = event.clientX;
      const containerWidth = this.$refs.tableGrid.offsetWidth
      const columnsFr = []
      for (const column of this.columns){columnsFr.push(parseInt(column.fr))}
      const gridWidthFr = this.gridWidthFr;
      const onMouseMove = (e) => {
        const deltaX = e.clientX - startX;
        let changeFr = Math.round((gridWidthFr * deltaX) / containerWidth)
        if (changeFr>=0){
          let sizeOther = 0
          for (let i = 0; i < index+1; i++) {
            sizeOther+=columnsFr[i]
          }
          for (let i = index+1; i < columnsFr.length; i++) {
            sizeOther+=this.minFr
          }
          const a = gridWidthFr - sizeOther - changeFr
          if (a<0){
            changeFr+=a
          }
          let newFr = columnsFr[index] + changeFr;
          this.positiveUpdateColumn(columnsFr, index + 1, -changeFr)
          this.columns[index].fr = `${newFr}fr`;
        }
        else {
          let sizeOther = 0
          for (let i = columnsFr.length-1; i > index; i--) {
            sizeOther+=columnsFr[i]
          }
          for (let i = index; i >= 0; i--) {
            sizeOther+=this.minFr
          }
          const a = gridWidthFr - sizeOther + changeFr
          if (a<0){
            changeFr-=a
          }
          let newFr = columnsFr[index] + changeFr;

          const remainsFr = newFr - this.minFr
          if (remainsFr<0){
            newFr = this.minFr
            this.negativeUpdateColumn(columnsFr, index - 1, remainsFr)
          }
          this.columns[index].fr = `${newFr}fr`;
          this.positiveUpdateColumn(columnsFr, index + 1, -changeFr)
        }
      }

      const onMouseUp = () => {
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
      }

      document.addEventListener('mousemove', onMouseMove);
      document.addEventListener('mouseup', onMouseUp);
    },
  }
}
</script>

<style scoped>
.table-container {
  width: 100%;
  overflow-x: auto;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.table {
  display: grid;
  min-width: 100%;
  background: #fff;
}

.cell {
  padding: 0;
  border-right: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
  min-height: 48px;
  display: flex;
  align-items: center;
}

.cell:last-child {
  border-right: none;
}

.cell-head {
  background: #f8f9fa;
  font-weight: 600;
  color: #2c3e50;
  position: sticky;
  top: 0;
  z-index: 10;
  border-bottom: 2px solid #e0e0e0;
}

.cell-head .header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  padding: 12px 16px;
  position: relative;
}

.header-text {
  font-size: 14px;
  font-weight: 600;
  color: #2c3e50;
  user-select: none;
}

.cell-body {
  background: #fff;
  transition: background-color 0.2s ease;
}

.cell-body:hover {
  background: #f8f9fa;
}

.cell-body .cell-content {
  padding: 12px 16px;
  width: 100%;
  font-size: 14px;
  color: #4a5568;
  line-height: 1.4;
}

.delete-btn {
  background: #e53e3e;
  color: white;
  border: none;
  padding: 6px 12px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease;
}

.delete-btn:hover {
  background: #c53030;
  transform: translateY(-1px);
}

.delete-btn:active {
  background: #9b2c2c;
  transform: translateY(0);
}

.resize-handle {
  position: absolute;
  height: 100%;
  width: 12px;
  top: 0;
  right: -6px;
  cursor: col-resize;
  z-index: 20;
  opacity: 0;
  transition: opacity 0.2s ease;
}

.resize-handle:hover,
.resize-handle:active {
  opacity: 1;
}

.resize-handle::after {
  content: '';
  position: absolute;
  top: 20%;
  bottom: 20%;
  right: 5px;
  width: 2px;
  background: #cbd5e0;
  border-radius: 1px;
  transition: background-color 0.2s ease;
}

.resize-handle:hover::after {
  background: #4299e1;
}

.resize-handle:active::after {
  background: #3182ce;
}

/* Убираем границы у последней строки */
.table :nth-last-child(-n + 4) {
  border-bottom: none;
}

/* Полосы для четных строк */
.cell-body:nth-child(even) {
  background: #fafbfc;
}

.cell-body:nth-child(even):hover {
  background: #f1f5f9;
}

/* Скроллбар */
.table-container::-webkit-scrollbar {
  height: 8px;
}

.table-container::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 0 0 4px 4px;
}

.table-container::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 4px;
}

.table-container::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}
</style>
