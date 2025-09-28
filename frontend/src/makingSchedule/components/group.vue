<template>
  <div class="group-container">
    <div class="group-title">
      <span>{{ group.title }}</span>
    </div>
    <couple
      v-for="(couple, idx) in group.schedule"
      :key="idx"
      :couple="couple"
      class="couple-row"
      @emitChange="emitChange"
    />
  </div>
</template>

<script>
import couple from './couple.vue'


export default {
  name: 'group',
  components:{
    couple
  },
  props: {
    group: {
      type: Object,
      required: true,
    }
  },
  methods:{
    emitChange(){
      this.$emit("emitChange")
    }
  }
}
</script>

<style scoped>
.group-container {
  font-family: Arial, sans-serif;
  height: 100%;
  width: 400px;
  border-right: 1px solid #e0e0e0;
  background: white;
}

.group-title {
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  background: #f0f0f0;
  border-bottom: 1px solid #ddd;
  height: 40px;
  align-content: center;
}

.couple-row {
  display: flex;
  gap: 10px;
  border-bottom: 1px solid #eee;
  height: 99px;
}

</style>
