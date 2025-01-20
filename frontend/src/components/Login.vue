<template>
  <div class="login-container">
    <div class="login-login">
      <div class="login-logo">
        <div class="login-frame20"></div>
        <div class="login-frame24"></div>
        <div class="login-frame21"></div>
        <div class="login-frame23"></div>
        <div class="login-frame22"></div>
      </div>
      <div class="login-frame37">
        <span class="login-text10">
          <span class="login-text11">não tem uma conta?</span>
          <span class="login-text23">cadastre-se</span>
        </span>
        <span class="login-text13">
          <span class="login-text14">esqueceu sua senha?</span>
          <span class="login-text23">clique aqui</span>
        </span>
        <div class="login-senha">
          <div class="login-texto1">
            <span class="login-text16">Senha</span>
          </div>
          <div class="login-svg-repoicon-carrier">
            <img
              :src="vector1869"
              alt="Vector1869"
              class="login-vector"
            />
          </div>
          <input class="login-text22" placeholder="lupita123" type="password" />
        </div>
        <div class="login-email">
          <div class="login-texto2">
            <span class="login-text18">Email</span>
          </div>
          <input class="login-text19" placeholder="example@example.com" type="email" />
        </div>
        <span class="login-text20">entrar</span>
        <div class="login-botopadrofontemaior" @click="handleLogin">
          <div class="login-boto">
            <span class="login-text21">ENTRAR</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import vector1869 from '../assets/vector1869-ka0j.svg';

export default defineComponent({
  name: 'Login',
  data() {
    return {
      vector1869,
    };
  },
  methods: {
    handleLogin() {
      // Implementar lógica de login aqui
      alert('Botão de login clicado!');
    },
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
  
  .login-login {
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
    top: 60px;
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
  
  .login-frame37 {
    top: 314px;
    left: 50%;
    transform: translateX(-50%);
    width: 440px;
    height: 397px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .login-text10 {
    top: 287px;
    color: rgba(123, 123, 123, 1);
    height: auto;
    position: absolute;
    font-size: 16px;
    font-style: Regular;
    text-align: left;
    font-family: 'Inter', sans-serif;
    font-weight: 400;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text11 {
    color: rgba(123, 123, 123, 1);
    font-weight: 400;
  }
  
  .login-text13 {
    top: 263px;
    color: rgba(123, 123, 123, 1);
    height: auto;
    position: absolute;
    font-size: 16px;
    font-style: Regular;
    text-align: left;
    font-family: 'Inter', sans-serif;
    font-weight: 400;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text14 {
    color: rgba(123, 123, 123, 1);
    font-weight: 400;
  }
  
  .login-senha {
    top: 198px;
    left: 0px;
    width: 440px;
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
  
  .login-text16 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: 'Inter', sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-svg-repoicon-carrier {
    top: 18px;
    left: 407px;
    width: 22.034px;
    height: 15.15px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 1;
  }
  
  .login-vector {
    width: 22px;
    height: 15px;
  }
  
  .login-text17 {
    top: 15.5px;
    left: 26px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    width: 360px;
    font-family: 'Inter', sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-email {
    top: 107px;
    left: 0px;
    width: 440px;
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
  
  .login-text18 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: 'Inter', sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .recuperarconta-text5 {
    top: 15.5px;
    left: 26px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    width: 360px;
    font-family: 'Inter', sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text20 {
    left: 145px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 50px;
    font-style: Bold;
    text-align: center;
    font-family: 'Inter', sans-serif;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-botopadrofontemaior {
    top: 352px;
    left: 50%;
    transform: translateX(-50%);
    width: 300px;
    height: 45px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .login-boto {
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
  }
  
  .login-text21 {
    top: 9px;
    left: 107px;
    color: rgba(255, 249, 238, 1);
    height: auto;
    position: absolute;
    font-size: 22px;
    font-style: Semi Bold;
    text-align: center;
    font-family: 'Inter', sans-serif;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-boto:hover {
    background-color: #373919;
  }
  
  .login-text19 {
    top: 15.5px;
    left: 26px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    width: 390px;
    font-family: 'Inter', sans-serif;
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
    width: 360px;
    font-family: 'Inter', sans-serif;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text23 {
    color: rgba(84, 86, 47, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: 'Inter', sans-serif;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
  
  .login-text23:hover {
    text-decoration: underline;
  }
  </style>
  