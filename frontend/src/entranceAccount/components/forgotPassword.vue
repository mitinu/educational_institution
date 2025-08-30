<template>
  <div>
    <input
      type="email"
      id="email"
      placeholder="email"
    >
    <button
      type="button"
    >
      получить сообщение
    </button>
  </div>
  <div>
    <input
      type="text"
      id="code"
      placeholder="code"
    >
    <button
      type="button"
    >
      получить сообщение еще раз
    </button>
  </div>
  <div>
    <input
      type="password"
      id="password"
      placeholder="password"
    >
    <input
      type="password"
      id="repeatPassword"
      placeholder="repeatPassword"
    >
    <button
      type="button"
    >
      поменять пароль
    </button>
  </div>
</template>


<script>
export default {
  name: 'forgotPassword'
}
</script>

