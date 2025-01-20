<template>
    <div class="login-container">
      <div class="login-editar-senhacliente">
        <div class="login-formulrio">
          <span class="login-text10">
            <span class="login-text11">
              Deseja voltar à visualização do perfil?
            </span>
            <span class="login-text28" @click="voltarPerfil">Clique aqui</span>
          </span>
          <div class="login-confirmarnovasenha">
            <div class="login-texto1">
              <span class="login-text13">Confirmar nova senha</span>
            </div>
            <div class="login-svg-repoicon-carrier1">
              <img
                src="../assets/vector1861-moai.svg"
                alt="Ícone de Confirmação de Senha"
                class="login-vector1"
              />
            </div>
            <input
              class="login-text14"
              v-model="confirmarSenha"
              type="password"
              placeholder="drjoao1234"
            />
            <span v-if="errors.confirmarSenha" class="error-text">{{ errors.confirmarSenha }}</span>
          </div>
          <div class="login-novasenha">
            <div class="login-texto2">
              <span class="login-text15">Nova senha</span>
            </div>
            <div class="login-svg-repoicon-carrier2">
              <img
                src="../assets/vector1861-9zp.svg"
                alt="Ícone de Nova Senha"
                class="login-vector2"
              />
            </div>
            <input
              class="login-text14"
              v-model="novaSenha"
              type="password"
              placeholder="drjoao1234"
            />
            <span v-if="errors.novaSenha" class="error-text">{{ errors.novaSenha }}</span>
          </div>
          <div class="login-senha">
            <div class="login-texto3">
              <span class="login-text17">Senha atual</span>
            </div>
            <div class="login-svg-repoicon-carrier3">
              <img
                src="../assets/vector1861-kcua.svg"
                alt="Ícone de Senha Atual"
                class="login-vector3"
              />
            </div>
            <input
              class="login-text14"
              v-model="senhaAtual"
              type="password"
              placeholder="drjoao123"
            />
            <span v-if="errors.senhaAtual" class="error-text">{{ errors.senhaAtual }}</span>
          </div>
          <span class="login-text19">Editar Senha Veterinário</span>
          <div class="login-botopadrofontemaior1">
            <div class="login-boto1" @click="confirmarEdicao">
              <span class="login-text20">CONFIRMAR</span>
            </div>
          </div>
        </div>
        <div class="login-navbarcliente">
          <img
            src="../assets/line13564-21wf.svg"
            alt="Linha Separadora"
            class="login-line1"
          />
          <span class="login-text21">Meus pets</span>
          <span class="login-text22">Perfil</span>
          <span class="login-text23">Consultas</span>
          <span class="login-text24">Vacinas</span>
          <span class="login-text25">Exames</span>
          <span class="login-text26">Produtos</span>
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
  import { useForm, useField } from 'vee-validate';
  import * as yup from 'yup';
  
  export default defineComponent({
    name: 'EditarSenhaVet',
    setup() {
      const router = useRouter();
      const confirmarSenha = ref('');
      const novaSenha = ref('');
      const senhaAtual = ref('');
  
      const schema = yup.object({
        confirmarSenha: yup
          .string()
          .oneOf([yup.ref('novaSenha')], 'As senhas não coincidem')
          .required('Confirmação de senha é obrigatória'),
        novaSenha: yup
          .string()
          .min(6, 'A nova senha deve ter pelo menos 6 caracteres')
          .required('Nova senha é obrigatória'),
        senhaAtual: yup
          .string()
          .min(6, 'A senha atual deve ter pelo menos 6 caracteres')
          .required('Senha atual é obrigatória'),
      });

  
      const { handleSubmit, errors } = useForm({
        validationSchema: schema,
      });
  
      const confirmarEdicao = handleSubmit(() => {
        // Implementar lógica de confirmação de edição
        // Exemplo: Validar campos e enviar para o backend
        if (!confirmarSenha.value || !novaSenha.value || !senhaAtual.value) {
          alert('Por favor, preencha todos os campos.');
          return;
        }
  
        // Simulação de requisição
        alert('Senha atualizada com sucesso!');
  
        // Redirecionar após confirmação
        router.push('/vet');
      });
  
      const voltarPerfil = () => {
        router.push('/vet');
      };
  
      const sair = () => {
        alert('Você saiu da conta.');
        router.push('/');
      };
  
      return {
        confirmarSenha,
        novaSenha,
        senhaAtual,
        confirmarEdicao,
        voltarPerfil,
        sair,
        errors,
      };
    },
  });
  </script>
  
  <style scoped>
  @import "../assets/styles/EditarSenhaStyles.css";
  </style>
  