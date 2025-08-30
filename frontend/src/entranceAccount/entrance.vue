<template>
  <authorization/>
  <registration/>
  <forgotPassword/>
</template>

<script>
import authorization from './components/authorization.vue'
import registration from './components/registration.vue'
import forgotPassword from './components/forgotPassword.vue'

export default {
  name: 'entrance',
  components: {
    authorization,
    registration,
    forgotPassword
  }
}
</script>
