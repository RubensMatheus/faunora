// src/router/index.ts
import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router';
import Login from '../components/Login.vue';
import RecuperarConta from '../components/RecuperarConta.vue';
// Importe outros componentes conforme necessário

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Login',
    component: Login,
  },
  {
    path: '/recuperar-conta',
    name: 'RecuperarConta',
    component: RecuperarConta,
  },
  // Adicione outras rotas aqui
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
