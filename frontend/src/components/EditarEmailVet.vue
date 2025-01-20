<template>
    <div class="login-container">
      <div class="login-editaremailcliente">
        <div class="login-formulrio">
          <span class="login-text10">
            <span class="login-text11">
              Deseja voltar à visualização do perfil?
            </span>
            <span class="login-text26" @click="voltarPerfil">
              Clique aqui
            </span>
          </span>
          <div class="login-novoemail">
            <div class="login-texto1">
              <span class="login-text13">Novo email</span>
            </div>
            <input
              class="login-text14"
              v-model="novoEmail"
              type="email"
              placeholder="example@example.com"
            />
          </div>
          <div class="login-senha">
            <div class="login-texto2">
              <span class="login-text15">Senha</span>
            </div>
            <input
              class="login-text16"
              v-model="senha"
              type="password"
              placeholder="senha123"
            />
            <div class="login-svg-repoicon-carrier">
              <img
                src="../assets/vector1861-d6p.svg"
                alt="Ícone de Senha"
                class="login-vector"
              />
            </div>
          </div>
          <span class="login-text17">Editar Email Veterinário</span>
          <div class="login-botopadrofontemaior1">
            <div class="login-boto1" @click="confirmarEdicao">
              <span class="login-text18">CONFIRMAR</span>
            </div>
          </div>
        </div>
        <div class="login-navbarcliente">
          <img
            src="../assets/line13564-0idp.svg"
            alt="Separador"
            class="login-line1"
          />
          <span class="login-text19">Meus pets</span>
          <span class="login-text20">Perfil</span>
          <span class="login-text21">Consultas</span>
          <span class="login-text22">Vacinas</span>
          <span class="login-text23">Exames</span>
          <span class="login-text24">Produtos</span>
          <div class="login-logo">
            <div class="login-frame20"></div>
            <div class="login-frame24"></div>
            <div class="login-frame21"></div>
            <div class="login-frame23"></div>
            <div class="login-frame22"></div>
          </div>
          <div class="login-botopadrofontemaior2">
            <div class="login-boto2" @click="sair">
              <span class="login-text25">SAIR</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  export default defineComponent({
    name: 'EditarEmailVet',
    setup() {
      const router = useRouter();
      const novoEmail = ref('');
      const senha = ref('');
  
      const voltarPerfil = () => {
        router.push('/vet');
      };
  
      const confirmarEdicao = () => {
        // Implementar lógica de confirmação de edição
        // Exemplo: Validar email, senha e enviar para o backend
        if (!novoEmail.value || !senha.value) {
          alert('Por favor, preencha todos os campos.');
          return;
        }
  
        // Simulação de requisição
        alert(`Email do veterinário atualizado com sucesso para ${novoEmail.value}!`);
        
        // Redirecionar após confirmação
        router.push('/vet');
      };
  
      const sair = () => {
        alert('Você saiu da conta.');
        router.push('/');
      };
  
      return {
        novoEmail,
        senha,
        voltarPerfil,
        confirmarEdicao,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/EditarEmailStyles.css';
  </style>
  