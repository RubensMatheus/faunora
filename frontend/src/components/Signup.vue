<!-- src/components/SignUp.vue -->
<template>
    <div class="login-container">
      <div class="login-cadastro">
        <div class="login-logo">
          <div class="login-frame20"></div>
          <div class="login-frame24"></div>
          <div class="login-frame21"></div>
          <div class="login-frame23"></div>
          <div class="login-frame22"></div>
        </div>
        <div class="login-frame38">
          <span class="login-text10">
            <span class="login-text11">Já tem uma conta?</span>
            <router-link to="/" class="login-text25">faça login</router-link>
          </span>
          <div class="login-confirmarsenha">
            <div class="login-texto1">
              <span class="login-text13">Confirmar senha</span>
            </div>
            <div class="login-svg-repoicon-carrier1">
              <img
                src="../assets/vector1869-inm.svg"
                alt="Vector1869"
                class="login-vector1"
              />
            </div>
            <input
              v-model="confirmarSenha"
              class="login-text14"
              type="password"
              placeholder="lupita123"
            />
          </div>
          <div class="login-senha">
            <div class="login-texto2">
              <span class="login-text15">Senha</span>
            </div>
            <div class="login-svg-repoicon-carrier2">
              <img
                src="../assets/vector1861-nlub.svg"
                alt="Vector1861"
                class="login-vector2"
              />
            </div>
            <input
              v-model="senha"
              class="login-text16"
              type="password"
              placeholder="lupita123"
            />
          </div>
          <div class="login-email">
            <div class="login-texto3">
              <span class="login-text17">Email</span>
            </div>
            <input
              v-model="email"
              class="login-text18"
              type="email"
              placeholder="example@example.com"
            />
          </div>
          <div class="login-sobrenome">
            <div class="login-texto4">
              <span class="login-text19">Sobrenome</span>
            </div>
            <input
              v-model="sobrenome"
              class="login-text20"
              type="text"
              placeholder="Batista"
            />
          </div>
          <div class="login-nome">
            <div class="login-texto5">
              <span class="login-text21">Nome</span>
            </div>
            <input
              v-model="nome"
              class="login-text22"
              type="text"
              placeholder="Giovanna"
            />
          </div>
          <span class="login-text23">Criar Conta</span>
          <!-- <div class="login-botopadrofontemaior"> -->
          
          <!-- </div> -->
          <div class="login-botopadrofontemaior">
            <button class="login-boto" @click="cadastrar">
              <span class="login-text24">CADASTRAR</span>
            </button>
            <div class="home-sexo">
            <div class="home-validadedo-laudo2">
              <span class="home-text21">Tipo conta:</span>
            </div>
            <div class="radio-group">
              <label class="radio-item">
                <input 
                  type="radio" 
                  name="tipo" 
                  value="TUTOR" 
                  v-model="tipo" 
                />
                <span class="custom-radio"></span>
                TUTOR
              </label>
              <label class="radio-item">
                <input 
                  type="radio" 
                  name="tipo" 
                  value="VETERINARIO" 
                  v-model="tipo" 
                />
                <span class="custom-radio"></span>
                VETERINARIO
              </label>
              </div>
            </div>
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
  name: 'SignUp',
  setup() {
    const router = useRouter();
    const nome = ref('');
    const sobrenome = ref('');
    const email = ref('');
    const senha = ref('');
    const confirmarSenha = ref('');
    const tipo = ref('');

    const cadastrar = async () => {
      if (
        nome.value &&
        sobrenome.value &&
        email.value &&
        senha.value &&
        confirmarSenha.value &&
        tipo.value
      ) {
        if (senha.value === confirmarSenha.value) {
          try {
            // Usando o apiClient para fazer a requisição
            const response = await apiClient.post('/users/registrar', {
              nome: nome.value,
              sobrenome: sobrenome.value,
              email: email.value,
              senha: senha.value,
              confirmarSenha: confirmarSenha.value,
              tipo: tipo.value
            });

            console.log(response.data); // Dados retornados pelo backend
            alert('Conta criada com sucesso!');
            router.push('/'); // Redireciona para a página de login
          } catch (error: any) {
            console.error(error);
            if (error.response && error.response.data) {
              const mensagens = Object.values(error.response.data);
              const mensagemFinal = mensagens.join('\n');
              alert(`Erro: \n${mensagemFinal}`);
            } else {
              alert('Erro ao criar conta. Tente novamente mais tarde.');
            }
          }
        } else {
          alert('As senhas não correspondem.');
        }
      } else {
        alert('Por favor, preencha todos os campos.');
      }
    };

    return {
      nome,
      sobrenome,
      email,
      senha,
      confirmarSenha,
      tipo,
      cadastrar,
    };
  },
});
</script>
  

  
  <style scoped>
  .login-container {
    width: 100%;
    display: flex;
    overflow: auto;
    min-height: 100vh;
    align-items: center;
    flex-direction: column;
  }
  
  .login-cadastro {
    width: 100%;
    height: 1024px;
    display: flex;
    overflow: hidden;
    position: relative;
    align-items: flex-start;
    flex-shrink: 0;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .login-logo {
    top: 27px;
    left: 43px;
    width: 48px;
    height: 68px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .login-frame20 {
    top: 0px;
    left: 0px;
    width: 24px;
    height: 22px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 100px 0 0 100px;
    background-color: rgba(71, 92, 54, 1);
  }
  
  .login-frame24 {
    top: 0px;
    left: 24px;
    width: 24px;
    height: 22px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 0 100px 100px 0;
    background-color: rgba(146, 151, 129, 1);
  }
  
  .login-frame21 {
    top: 22px;
    left: 0px;
    width: 24px;
    height: 24px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 100px 0 0 100px;
    background-color: rgba(151, 166, 126, 1);
  }
  
  .login-frame23 {
    top: 22px;
    left: 24px;
    width: 24px;
    height: 24px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 100px;
    background-color: rgba(74, 94, 56, 1);
  }
  
  .login-frame22 {
    top: 46px;
    left: 0px;
    width: 24px;
    height: 22px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 100px 0 100px 100px;
    background-color: rgba(55, 70, 40, 1);
  }
  
  .login-frame38 {
    top: 0px;
    left: 0px;
    right: 0px;
    width: 616px;
    bottom: 0px;
    height: 466px;
    margin: auto;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .login-text10 {
    top: 355px;
    color: rgba(123, 123, 123, 1);
    height: auto;
    position: absolute;
    font-size: 16px;
    font-style: Regular;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 400;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text11 {
    color: rgba(123, 123, 123, 1);
    font-weight: 400;
  }
  
  .login-confirmarsenha {
    top: 290px;
    left: 315px;
    width: 300px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
  
  .login-texto1 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 144px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .login-text13 {
    color: rgb(123, 123, 123);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: "Inter", sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-svg-repoicon-carrier1 {
    top: 17.43px;
    left: 267.98px;
    width: 22.03px;
    height: 15.15px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 1;
  }
  
  .login-vector1 {
    top: 0px;
    left: 0px;
    width: 22px;
    height: 15px;
    position: absolute;
  }
  
  .login-text14 {
    top: 15.5px;
    left: 26px;
    color: rgb(67, 67, 67);
    height: auto;
    position: absolute;
    width: 225px;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-senha {
    top: 290px;
    left: 0px;
    width: 300px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
  
  .login-texto2 {
    gap: 8px;
    top: -10px;
    left: 20px;
    width: 64px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .login-text15 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-svg-repoicon-carrier2 {
    top: 18.43px;
    left: 267.98px;
    width: 22.03px;
    height: 15.15px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 1;
  }
  
  .login-vector2 {
    top: 0px;
    left: 0px;
    width: 22px;
    height: 15px;
    position: absolute;
  }
  
  .login-text16 {
    top: 15.5px;
    left: 26px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    width: 250px;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-email {
    top: 199px;
    left: 0px;
    width: 615px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
  
  .login-texto3 {
    gap: 8px;
    top: -9px;
    left: 18px;
    width: 57px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .login-text17 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text18 {
    top: 15.5px;
    left: 26px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    width: 560px;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-sobrenome {
    top: 108px;
    left: 315px;
    width: 300px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
  
  .login-texto4 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 104px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .login-text19 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text20 {
    top: 15.5px;
    left: 26px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-nome {
    top: 108px;
    left: 0px;
    width: 300px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
  
  .login-texto5 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 62px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .login-text21 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text22 {
    top: 15.5px;
    left: 26px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text23 {
    left: 0px;
    color: rgb(67, 67, 67);
    right: 0px;
    width: 615px;
    height: auto;
    margin: auto;
    position: absolute;
    font-size: 50px;
    font-style: Bold;
    text-align: center;
    font-family: "Inter", sans-serif;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-botopadrofontemaior {
    top: 421px;
    left: 158px;
    width: 300px;
    height: 45px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .login-boto {
    top: 0px;
    left: 0px;
    width: 300px;
    height: 45px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 40px;
    background-color: rgba(84, 86, 47, 1);
    cursor: pointer;
    transition: background-color 0.3s ease;
    border: none;
  }
  
  .login-text24 {
    top: 9px;
    left: 84px;
    color: rgba(255, 249, 238, 1);
    height: auto;
    position: absolute;
    font-size: 22px;
    font-style: Semi Bold;
    text-align: center;
    font-family: Inter, sans-serif;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-boto:hover {
    background-color: #373919;
  }
  
  .login-text25 {
    color: rgba(84, 86, 47, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text25:hover {
    color: rgba(84, 86, 47, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter, sans-serif;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: underline;
  }
  
  @media (max-width: 768px) {
    .login-container {
      width: 100%;
      padding: 20px;
    }
  
    .login-cadastro {
      height: auto;
      flex-direction: column;
      align-items: center;
    }
  
    .login-frame38 {
      width: 100%;
      height: auto;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  
    .login-confirmarsenha,
    .login-senha,
    .login-email,
    .login-sobrenome,
    .login-nome {
      width: 100%;
      left: 0px;
    }
  
    .login-text5,
    .login-text14,
    .login-text16,
    .login-text18,
    .login-text20,
    .login-text22 {
      width: 100%;
    }
  
    .login-botopadrofontemaior {
      width: 100%;
      left: 0px;
      top: 350px;
    }
  
    .login-boto {
      width: 100%;
    }
  
    .login-text23 {
      width: 100%;
    }
  }

  .home-sexo {
    top: 139px;
    left: 236px;
    width: 203px;
    height: 47px;
  }

  .home-validadedo-laudo2 {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
    padding: 0 8px;
  }

  .home-text21 {
    color: rgba(123, 123, 123, 1);
    font-size: 16px;
    font-weight: 500;
    font-family: Inter, sans-serif;
    text-align: left;
  }

  .radio-group {
    display: flex;
    align-items: center;
    gap: 20px;
    margin-left: 9px;
    margin-top: 29px;
  }

  .radio-item {
    display: flex;
    align-items: center;
    cursor: pointer;
  }

  .radio-item input[type="radio"] {
    display: none;
  }

  .custom-radio {
    width: 22px;
    height: 22px;
    background-color: #d9d9d9;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: 10px;
    transition: background-color 0.3s;
  }

  .custom-radio::after {
    content: '';
    width: 13px;
    height: 13px;
    background-color: transparent;
    border-radius: 50%;
    transform: scale(1);
    transition: background-color 0.3s, transform 0.3s;
  }

  input[type="radio"]:checked + .custom-radio::after {
    background-color: #54562f;
    transform: scale(1.2);
  }
  </style>
  