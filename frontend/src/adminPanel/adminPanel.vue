<template>
  <group></group>
  <account></account>
</template>

<script>

import Group from "./components/group.vue";
import Account from "./components/account.vue";

export default {
  name: 'adminPanel',
  components: {
    group: Group,
    account: Account
  },


}
</script>

<style scoped>

</style>
