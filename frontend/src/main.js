import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { api } from './api';

// Создаем экземпляр приложения
const app = createApp(App);

// Настраиваем глобальные свойства ДО использования плагинов
app.config.globalProperties.$api = api;

// Подключаем плагины
app.use(store);
app.use(router);

// Монтируем приложение
app.mount('#app');
