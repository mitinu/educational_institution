<template>

</template>

<script>
export default {
  name: "myAlert",
  data(){
    return {
      showAlert: false,
      handleConfirm: function (){},
      handleCancel: function (){}
    }
  },
  methods:{
    createMyAlert(handleConfirm = function (){}, handleCancel = function (){}){
      this.showAlert = true
      this.handleConfirm = handleConfirm
      this.handleCancel = handleCancel
    },

  }


}
</script>

<style scoped>

</style>
