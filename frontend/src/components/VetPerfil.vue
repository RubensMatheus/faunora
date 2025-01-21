<template>
    <div class="login-container">
      <div class="login-perfilcliente">
        <!-- Formulário de Perfil do Veterinário (Vet) -->
        <div class="login-formulrio">
          <span class="login-text10">
            <span class="login-text11">Deseja excluir o perfil?</span>
            <router-link to="/excluir-perfil" class="login-text26">clique aqui</router-link>
          </span>
          <div class="login-confirmarsenha">
            <div class="login-texto1">
              <span class="login-text13">Email</span>
            </div>
            <div class="login-svg-repoicon-carrier1">
              <img
                alt="Vector1861"
                src="../assets/vector1861-j5am.svg"
                class="login-vector1"
              />
              <img
                alt="Vector1861"
                src="../assets/vector1861-lplp.svg"
                class="login-vector2"
              />
            </div>
            <span class="login-text14">{{ email }}</span>
          </div>
          <div class="login-nome">
            <div class="login-texto2">
              <span class="login-text15">Nome e sobrenome</span>
            </div>
            <div class="login-svg-repoicon-carrier2">
              <img
                alt="Vector1861"
                src="../assets/vector1861-yntm.svg"
                class="login-vector3"
              />
              <img
                alt="Vector1861"
                src="../assets/vector1861-ym2.svg"
                class="login-vector4"
              />
            </div>
            <span class="login-text16">{{ nomeCompleto }}</span>
          </div>
          <span class="login-text17">Visualizar Perfil</span>
          <div class="login-botopadrofontemaior1">
            <button class="login-boto1" @click="editarSenha">
              <span class="login-text18">EDITAR SENHA</span>
            </button>
          </div>
        </div>
        <div class="login-navbarcliente">
          <span class="login-text30">Agenda</span>
          <span class="login-text27">Consultas</span>
          <span class="login-text28">Vacinas</span>
          <span class="login-text29">Exames</span>
          <div class="login-logo">
            <div class="login-frame20"></div>
            <div class="login-frame24"></div>
            <div class="login-frame21"></div>
            <div class="login-frame23"></div>
            <div class="login-frame22"></div>
          </div>
          <div class="login-botopadrofontemaior2">
            <button class="login-boto2" @click="sair">
              <span class="login-text25">SAIR</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { useRouter } from 'vue-router';
  import apiClient from '../services/api';
  
  export default defineComponent({
    name: 'VetPage',
    setup() {
      const router = useRouter();
      const email = ref('vet@gmail.com');
      const nomeCompleto = ref('Dr. João Silva');

      const resgatarUsuario = async () => {
        try {
          const userId = Number(sessionStorage.getItem('userID'));
          const response = await apiClient.get(`/users/${userId}`);
          const userData = response.data;
          email.value = userData.email;
          nomeCompleto.value = userData.nome;
        } catch (error) {
          console.error('Erro ao buscar o perfil do usuário:', error);
          alert('Erro ao carregar o perfil do usuário. Tente novamente mais tarde.');
        }
      }
  
      const editarSenha = () => {
        // Lógica para editar a senha
        alert('Redirecionando para a página de edição de senha...');
        router.push('/editar-senha');
      };
  
      const sair = () => {
        sessionStorage.removeItem('authToken');
        sessionStorage.removeItem('userTipo');
        alert('Você saiu com sucesso!');
        router.push('/');
      };
  
      return {
        email,
        nomeCompleto,
        editarSenha,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/perfil.css';
  </style>
  