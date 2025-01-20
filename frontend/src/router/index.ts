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
  },
  {
    path: '/vet',
    name: 'VetPage',
    component: VetPage,
  },
  {
    path: '/meus-pets',
    name: 'MeusPets',
    component: MeusPets,
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
  },
  {
    path: '/home-vet',
    name: 'HomeVet',
    component: HomeVet,
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
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
