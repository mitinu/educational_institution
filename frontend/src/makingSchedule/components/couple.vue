<template>
  <div>
    <div class="subject-professor">
      <input type="text" @change="emitChange" v-model="couple.academicSubject" placeholder="Предмет">
      <input type="text" @change="emitChange" v-model="couple.professor" placeholder="Преподаватель">
    </div>
    <div class="office">
      <input type="text" @change="emitChange" v-model="couple.office" placeholder="Кабинет">
    </div>
  </div>
</template>

<script>
export default {
  name: 'couple',
  props: {
    couple: {
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

.subject-professor {
  display: flex;
  align-self: anchor-center;
  flex-direction: column;
  gap: 8px;
  flex: 1;
}

.office {
  display: flex;
  align-items: center;
}

input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

input:focus {
  outline: none;
  border-color: #4285f4;
  box-shadow: 0 0 0 2px rgba(66, 133, 244, 0.2);
}
</style>
