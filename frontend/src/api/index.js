import axios from 'axios';

// Создаем экземпляр axios с базовыми настройками
const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
});

// API методы
export const api = {
  // Получение групп
  getGroups: () => apiClient.get('/getGroups')
    .then(response =>response.data),
  addGroup: (data) => apiClient.post('/addGroup', data)
    .then(response => response.data),
  deleteGroup: (id) => apiClient.get(`/deleteGroup/${id}`)
    .then(response => response.data),

  getAccounts: () => apiClient.get(`/getAccounts`)
    .then(response => response.data),
  addAccount: (data) => apiClient.post('/addAccount', data)
    .then(response => response.data),
  deleteAccount: (id) => apiClient.get(`/deleteAccount/${id}`)
    .then(response => response.data),

  getSchedule: (data) => apiClient.post('/getSchedule', data)
    .then(response => response.data),

  getSchedulesGroup: (id) => apiClient.get(`/getSchedulesGroup/${id}`)
    .then(response => response.data),

  setSchedule: (data) => apiClient.post('/setSchedule', data)
    .then(response => response.data),


};

export default api;
