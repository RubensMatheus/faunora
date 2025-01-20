<template>
  <div class="recuperarconta-container">
    <div class="recuperarconta-redefinirsenha">
      <!-- Logo -->
      <div class="recuperarconta-logo">
        <div class="recuperarconta-frame20"></div>
        <div class="recuperarconta-frame24"></div>
        <div class="recuperarconta-frame21"></div>
        <div class="recuperarconta-frame23"></div>
        <div class="recuperarconta-frame22"></div>
      </div>

      <!-- Formulário de Redefinição de Senha -->
      <div class="recuperarconta-frame36">
        <!-- Texto de Redefinição -->
        <span class="recuperarconta-text1">
          <span class="recuperarconta-text2">Lembrou da sua senha?</span>
          <span class="recuperarconta-text10" @click="navegarLogin">Faça login</span>
        </span>

        <!-- Campo de Confirmar Nova Senha -->
        <div class="recuperarconta-confirmarsenha">
          <div class="recuperarconta-texto1">
            <span class="recuperarconta-text4">Confirmar nova senha</span>
          </div>
          <div class="recuperarconta-svg-repoicon-carrier1">
            <img
              src="../assets/vector1869-yr3o.svg"
              alt="Ícone de confirmação"
              class="recuperarconta-vector1"
            />
          </div>
          <input
            class="recuperarconta-text5"
            placeholder="lupita123"
            v-model="confirmPassword"
            type="password"
          />
        </div>

        <!-- Campo de Nova Senha -->
        <div class="recuperarconta-senha">
          <div class="recuperarconta-texto2">
            <span class="recuperarconta-text6">Nova senha</span>
          </div>
          <div class="recuperarconta-svg-repoicon-carrier2">
            <img
              src="../assets/vector1869-727s.svg"
              alt="Ícone de senha"
              class="recuperarconta-vector2"
            />
          </div>
          <input
            class="recuperarconta-text5"
            placeholder="lupita123"
            v-model="newPassword"
            type="password"
          />
        </div>

        <!-- Título da Página -->
        <span class="recuperarconta-text8">Redefinir senha</span>

        <!-- Botão de Confirmar -->
        <div class="recuperarconta-botopadrofontemaior">
          <div class="recuperarconta-boto" @click="confirmResetPassword">
            <span class="recuperarconta-text9">CONFIRMAR</span>
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
  name: 'ResetPassword',
  setup() {
    const router = useRouter();
    const confirmPassword = ref('');
    const newPassword = ref('');

    const navegarLogin = () => {
      router.push('/'); // Atualize a rota conforme necessário
    };

    const confirmResetPassword = () => {
      if (newPassword.value !== confirmPassword.value) {
        alert('As senhas não coincidem. Por favor, tente novamente.');
        return;
      }

      if (newPassword.value.length < 6) {
        alert('A senha deve ter pelo menos 6 caracteres.');
        return;
      }

      // Aqui você deve adicionar a lógica para redefinir a senha, como uma chamada à API.
      alert('Senha redefinida com sucesso!');
      router.push('/'); // Redireciona para a página de login após redefinição
    };

    return {
      confirmPassword,
      newPassword,
      navegarLogin,
      confirmResetPassword,
    };
  },
});
</script>

<style scoped>
@import '../assets/styles/RecuperarContaStyles.css';
</style>
