<template>
    <div class="recuperarconta-container">
      <div class="recuperarconta-recuperarconta">
        <div class="recuperarconta-logo">
          <!-- ... outras divs ... -->
        </div>
        <div class="recuperarconta-frame35">
          <span class="recuperarconta-text1">
            Como funciona: te enviaremos um link para redefinição de senha para seu
            endereço de email!
          </span>
          <span class="recuperarconta-text2">
            <span class="recuperarconta-text3">Lembrou da sua senha?</span>
            <router-link to="/" class="recuperarconta-text4">faça login</router-link>
          </span>
          <div class="recuperarconta-senha">
            <div class="recuperarconta-texto">
              <span class="recuperarconta-text9">Email</span>
            </div>
            <input 
              v-model="email" 
              class="recuperarconta-text5" 
              type="email" 
              placeholder="example@example.com" 
            />
          </div>
          <span class="recuperarconta-text7">Recuperar Conta</span>
          <div class="recuperarconta-botopadrofontemaior">
            <button class="recuperarconta-boto" @click="enviarEmail">
              <span class="recuperarconta-text8">ENVIAR EMAIL</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  export default defineComponent({
    name: 'RecuperarConta',
    setup() {
      const email = ref('');
      const router = useRouter();
  
      const enviarEmail = async () => {
        if (email.value) {
          try {
            const response = await fetch('https://sua-api.com/recuperar-senha', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: JSON.stringify({ email: email.value }),
            });
  
            if (response.ok) {
              alert('Email de recuperação enviado!');
              router.push('/');
            } else {
              alert('Falha ao enviar o email de recuperação.');
            }
          } catch (error) {
            console.error('Erro ao enviar o email:', error);
            alert('Ocorreu um erro. Por favor, tente novamente mais tarde.');
          }
        } else {
          alert('Por favor, insira um endereço de email válido.');
        }
      };
  
      return {
        email,
        enviarEmail,
      };
    },
  });
  </script>
  
  <style scoped>
  .recuperarconta-container {
    width: 100%;
    min-height: 100vh;
    display: flex;
    overflow: auto;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    background: var(--dl-color-theme-neutral-light, #f0f0f0);
  }
  
  .recuperarconta-recuperarconta {
    width: 100%;
    max-width: 1440px;
    height: 100%;
    display: flex;
    position: relative;
    align-items: flex-start;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .recuperarconta-logo {
    top: 27px;
    left: 43px;
    width: 48px;
    height: 68px;
    display: flex;
    position: absolute;
    align-items: flex-start;
  }
  
  .recuperarconta-frame20 {
    width: 24px;
    height: 22px;
    position: absolute;
    border-radius: 100px 0 0 100px;
    background-color: rgba(71, 92, 54, 1);
  }
  
  .recuperarconta-frame24 {
    top: 0px;
    left: 24px;
    width: 24px;
    height: 22px;
    position: absolute;
    border-radius: 0 100px 100px 0;
    background-color: rgba(146, 151, 129, 1);
  }
  
  .recuperarconta-frame21 {
    top: 22px;
    left: 0px;
    width: 24px;
    height: 24px;
    position: absolute;
    border-radius: 100px 0 0 100px;
    background-color: rgba(151, 166, 126, 1);
  }
  
  .recuperarconta-frame23 {
    top: 22px;
    left: 24px;
    width: 24px;
    height: 24px;
    position: absolute;
    border-radius: 100px;
    background-color: rgba(74, 94, 56, 1);
  }
  
  .recuperarconta-frame22 {
    top: 46px;
    left: 0px;
    width: 24px;
    height: 22px;
    position: absolute;
    border-radius: 100px 0 100px 100px;
    background-color: rgba(55, 70, 40, 1);
  }
  
  .recuperarconta-frame35 {
    width: 440px;
    height: 319px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
  
  .recuperarconta-text1 {
    color: rgba(123, 123, 123, 1);
    width: 200em;
    font-size: 16px;
    text-align: justify;
    font-family: 'Inter', sans-serif;
    font-weight: 400;
    line-height: normal;
    text-decoration: underline;
  }
  
  .recuperarconta-text2 {
    color: rgba(123, 123, 123, 1);
    font-size: 16px;
    text-align: left;
    font-family: 'Inter', sans-serif;
    font-weight: 400;
  }
  
  .recuperarconta-text3 {
    color: rgba(123, 123, 123, 1);
    font-weight: 400;
  }
  
  .recuperarconta-text4 {
    color: rgba(84, 86, 47, 1);
    font-size: 16px;
    text-align: left;
    font-family: 'Inter', sans-serif;
    font-weight: 700;
    text-decoration: none;
    cursor: pointer;
  }
  
  .recuperarconta-text4:hover {
    text-decoration: underline;
  }
  
  .recuperarconta-senha {
    width: 440px;
    height: 51px;
    display: flex;
    position: relative;
    align-items: flex-start;
    border: 1px solid rgba(123, 123, 123, 1);
    border-radius: 8px;
    margin-top: 20px;
  }
  
  .recuperarconta-texto {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 0 8px;
    position: absolute;
    top: -10px;
    left: 20px;
    background-color: rgba(255, 255, 255, 1);
    gap: 8px;
  }
  
  .recuperarconta-text9 {
    color: rgba(123, 123, 123, 1);
    font-size: 16px;
    font-weight: 500;
  }
  
  .recuperarconta-text5 {
    padding: 15.5px 26px;
    color: rgba(67, 67, 67, 1);
    width: 390px;
    font-size: 18px;
    font-weight: 500;
    font-family: 'Inter', sans-serif;
    border: none;
    outline: none;
  }
  
  .recuperarconta-text7 {
    color: rgba(67, 67, 67, 1);
    font-size: 50px;
    font-weight: 700;
    font-family: 'Inter', sans-serif;
    position: absolute;
    top: -60px;
    left: 0px;
  }
  
  .recuperarconta-botopadrofontemaior {
    width: 300px;
    height: 45px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    top: 274px;
    left: 70px;
  }
  
  .recuperarconta-boto {
    width: 300px;
    height: 45px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 40px;
    background-color: rgba(84, 86, 47, 1);
    cursor: pointer;
    transition: background-color 0.3s ease;
    border: none;
  }
  
  .recuperarconta-boto:hover {
    background-color: #373919;
  }
  
  .recuperarconta-text8 {
    color: rgba(255, 249, 238, 1);
    font-size: 22px;
    font-weight: 700;
    font-family: 'Inter', sans-serif;
  }
  
  @media (max-width: 768px) {
    .recuperarconta-container {
      width: 100%;
      padding: 20px;
    }
  
    .recuperarconta-frame35 {
      width: 100%;
    }
  
    .recuperarconta-senha {
      width: 100%;
    }
  
    .recuperarconta-text5 {
      width: 100%;
    }
  
    .recuperarconta-botopadrofontemaior {
      width: 100%;
    }
  
    .recuperarconta-boto {
      width: 100%;
    }
  }
  </style>
  