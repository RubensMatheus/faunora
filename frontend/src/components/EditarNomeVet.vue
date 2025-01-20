<template>
    <div class="login-container">
      <div class="login-editarnomecliente">
        <div class="login-formulrio">
          <span class="login-text10">
            <span class="login-text11">
              Deseja voltar à visualização do perfil?
            </span>
            <span class="login-text28" @click="voltarPerfil">Clique aqui</span>
          </span>
          <div class="login-confirmarnovasenha">
            <div class="login-texto1">
              <span class="login-text13">Novo sobrenome</span>
            </div>
            <input
              class="login-text14"
              v-model="novoSobrenome"
              placeholder="Silva"
            />
          </div>
          <div class="login-novasenha">
            <div class="login-texto2">
              <span class="login-text15">Novo nome</span>
            </div>
            <input
              class="login-text14"
              v-model="novoNome"
              placeholder="Dr. João"
            />
          </div>
          <div class="login-senha">
            <div class="login-texto3">
              <span class="login-text17">Senha</span>
            </div>
            <div class="login-svg-repoicon-carrier">
              <img
                src="../assets/vector1861-gcsi.svg"
                alt="Ícone de Senha"
                class="login-vector"
              />
            </div>
            <input
              class="login-text18"
              v-model="senha"
              type="password"
              placeholder="senha123"
            />
          </div>
          <span class="login-text19">Editar Nome Veterinário</span>
          <div class="login-botopadrofontemaior1">
            <div class="login-boto1" @click="confirmarEdicao">
              <span class="login-text20">CONFIRMAR</span>
            </div>
          </div>
        </div>
        <div class="login-navbarcliente">
          <img
            src="../assets/line13564-vhuq.svg"
            alt="Linha Separadora"
            class="login-line1"
          />
          <span class="login-text29">Perfil</span>
          <span class="login-text30">Consultas</span>
          <span class="login-text31">Vacinas</span>
          <span class="login-text32">Exames</span>
          <div class="login-logo">
            <div class="login-frame20"></div>
            <div class="login-frame24"></div>
            <div class="login-frame21"></div>
            <div class="login-frame23"></div>
            <div class="login-frame22"></div>
          </div>
          <div class="login-botopadrofontemaior2">
            <div class="login-boto2" @click="sair">
              <span class="login-text27">SAIR</span>
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
    name: 'EditarNomeVet',
    setup() {
      const router = useRouter();
      const novoSobrenome = ref('');
      const novoNome = ref('');
      const senha = ref('');
  
      const voltarPerfil = () => {
        router.push('/vet');
      };
  
      const confirmarEdicao = () => {
        // Implementar lógica de confirmação de edição
        // Exemplo: Validar campos e enviar para o backend
        if (!novoSobrenome.value || !novoNome.value || !senha.value) {
          alert('Por favor, preencha todos os campos.');
          return;
        }
  
        // Simulação de requisição
        alert(`Nome atualizado com sucesso para ${novoNome.value} ${novoSobrenome.value}!`);
  
        // Redirecionar após confirmação
        router.push('/vet');
      };
  
      const sair = () => {
        alert('Você saiu da conta.');
        router.push('/');
      };
  
      return {
        novoSobrenome,
        novoNome,
        senha,
        voltarPerfil,
        confirmarEdicao,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import "../assets/styles/EditarNomeStyles.css";
  </style>
  