import axios from 'axios';

// Создаем экземпляр axios с базовыми настройками
const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
});
//
// // Интерцепторы для обработки запросов/ответов
// apiClient.interceptors.request.use(
//   config => {
//     // Добавляем токен авторизации, если есть
//     const token = localStorage.getItem('authToken');
//     if (token) {
//       config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
//   },
//   error => {
//     return Promise.reject(error);
//   }
// );
//
// apiClient.interceptors.response.use(
//   response => response,
//   error => {
//     // Обработка ошибок
//     if (error.response?.status === 401) {
//       // Перенаправление на страницу логина
//       window.location.href = '/login';
//     }
//     return Promise.reject(error);
//   }
// );

// API методы
export const api = {
  // Получение групп
  getGroups: () => apiClient.get('/getGroups')
    .then(response =>response.data),

  addGroup: (data) => apiClient.post('/addGroup', data)
    .then(response => response.data),


  getAccount: () => apiClient.get(`/getAccount`)
    .then(response => response.data),

  addAccount: (data) => apiClient.post('/addGroup', data)
    .then(response => response.data),

  getSchedule: (data) => apiClient.post('/getSchedule', data)
    .then(response => response.data),

  getSchedulesGroup: (id) => apiClient.get(`/getSchedulesGroup/${id}`)
    .then(response => response.data),

  setSchedule: (data) => apiClient.post('/setSchedule', data)
    .then(response => response.data),


};

export default api;
