<template>

</template>

<script>
export default {
  name: "funcModal",
  data() {
    return {
      visibilityModel: false
    }
  },
  methods: {

    viewModel() {
      this.visibilityModel = true
    },
    closeModal() {
      this.visibilityModel = false
    }
  }
}
</script>

<style scoped>

</style>
