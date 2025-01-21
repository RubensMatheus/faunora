// src/router/index.ts
import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router';
import Login from '../components/Login.vue';
import RecuperarConta from '../components/RecuperarConta.vue';
import SignUp from '../components/Signup.vue';
import TutorPage from '../components/TutorPerfil.vue';
import VetPage from '../components/VetPerfil.vue';
import MeusPets from '../components/MeusPets.vue';
import ConsultasTutor from '../components/ConsultasTutor.vue';
import ConsultasVet from '../components/ConsultasVet.vue';
import ExamesTutor from '../components/ExamesTutor.vue';
import ExamesVet from '../components/ExamesVet.vue';
import HomeTutor from '../components/HomeTutor.vue';
import HomeVet from '../components/HomeVet.vue';
import VacinaTutor from '../components/VacinaTutor.vue';
import VacinaVet from '../components/VacinaVet.vue';
import EditarEmailTutor from '../components/EditarEmailTutor.vue';
import EditarEmailVet from '../components/EditarEmailVet.vue';
import EditarNomeTutor from '../components/EditarNomeTutor.vue';
import EditarNomeVet from '../components/EditarNomeVet.vue';
import EditarSenhaTutor from '../components/EditarSenhaTutor.vue';
import EditarSenhaVet from '../components/EditarSenhaVet.vue';

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
  {
    path: '/cadastro',
    name: 'SignUp',
    component: SignUp,
  },
  {
    path: '/tutor',
    name: 'TutorPage',
    component: TutorPage,
    meta: { requiresAuth: true, role: 'TUTOR' },
  },
  {
    path: '/vet',
    name: 'VetPage',
    component: VetPage,
    meta: { requiresAuth: true, role: 'VETERINARIO' },
  },
  {
    path: '/meus-pets',
    name: 'MeusPets',
    component: MeusPets,
    meta: { requiresAuth: true, role: 'TUTOR' },
  },
  {
    path: '/consultas-tutor',
    name: 'ConsultasTutor',
    component: ConsultasTutor,
  },
  {
    path: '/consultas-vet',
    name: 'ConsultasVet',
    component: ConsultasVet,
  },
  {
    path: '/exames-tutor',
    name: 'ExamesTutor',
    component: ExamesTutor,
  },
  {
    path: '/exames-vet',
    name: 'ExamesVet',
    component: ExamesVet,
  },
  {
    path: '/home-tutor',
    name: 'HomeTutor',
    component: HomeTutor,
    meta: { requiresAuth: true, role: 'TUTOR' },
  },
  {
    path: '/home-vet',
    name: 'HomeVet',
    component: HomeVet,
    meta: { requiresAuth: true, role: 'VETERINARIO' },
  },
  {
    path: '/vacina-tutor',
    name: 'VacinaTutor',
    component: VacinaTutor,
  },
  {
    path: '/vacina-vet',
    name: 'VacinaVet',
    component: VacinaVet,
  },
  {
    path: '/editar-email-tutor',
    name: 'EditarEmailTutor',
    component: EditarEmailTutor,
  },
  {
    path: '/editar-email-vet',
    name: 'EditarEmailVet',
    component: EditarEmailVet,
  },
  {
    path: '/editar-nome-tutor',
    name: 'EditarNomeTutor',
    component: EditarNomeTutor,
  },
  {
    path: '/editar-nome-vet',
    name: 'EditarNomeVet',
    component: EditarNomeVet,
  },
  {
    path: '/editar-senha-tutor',
    name: 'EditarSenhaTutor',
    component: EditarSenhaTutor,
    meta: { requiresAuth: true },
  },
  {
    path: '/editar-senha-vet',
    name: 'EditarSenhaVet',
    component: EditarSenhaVet,
    meta: { requiresAuth: true },
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('authToken');
  const userTipo = sessionStorage.getItem('userTipo');

  if (to.meta.requiresAuth) {
    if (!token) {
      alert('Você precisa estar autenticado para acessar esta página.');
      next('/');
      return;
    }

    if (to.meta.role && to.meta.role !== userTipo) {
      alert('Você não tem permissão para acessar esta página.');
      next('/');
      return;
    }
  }

  next();
});

export default router;
