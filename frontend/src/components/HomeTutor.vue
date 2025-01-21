<template>
  <div class="container">
    <div class="homepagecliente">
      <div class="frame6">
        <div class="boto1" @click="cadastrarNovoPet">
          <span class="text10">cadastrar novo pet</span>
        </div>
      </div>
      <span class="text11">bem-vindo(a) ao faunora, maria!</span>
      <div class="boto-vazado" @click="verProximasConsultas">
        <span class="text12">ver minhas próximas consultas</span>
      </div>
      <span class="text13">
        aqui, você facilita sua experiência em nossa clínica e cuida melhor
        dos seus bichinhos!
      </span>
      <div class="navbarcliente">
        <img
          src="../assets/line13563-bh5a.svg"
          alt="Line13563"
          class="line1"
        />
        <span class="text14" @click="navegar('meus-pets')">Meus pets</span>
        <span class="text15" @click="navegar('tutor')">Perfil</span>
        <span class="text16" @click="navegar('consultas-tutor')">Consultas</span>
        <span class="text17" @click="navegar('vacina-tutor')">Vacinas</span>
        <span class="text18" @click="navegar('exames-tutor')">Exames</span>
        <span class="text19" @click="navegar('Produtos')">Produtos</span>
        <div class="logo">
          <div class="frame20"></div>
          <div class="frame24"></div>
          <div class="frame21"></div>
          <div class="frame23"></div>
          <div class="frame22"></div>
        </div>
        <div class="botopadrofontemaior">
          <div class="boto2" @click="sair">
            <span class="text20">SAIR</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'HomeTutor',
  setup() {
    const router = useRouter();

    const cadastrarNovoPet = () => {
      router.push('/meus-pets'); // Atualize a rota conforme necessário
    };

    const verProximasConsultas = () => {
      router.push('/proximas-consultas'); // Atualize a rota conforme necessário
    };

    const navegar = (destino: string) => {
      const rota = `/${destino.toLowerCase().replace(' ', '-')}`;
      router.push(rota);
    };

    const sair = () => {
      sessionStorage.removeItem('authToken');
      sessionStorage.removeItem('userTipo');
      alert('Você saiu com sucesso!');
      router.push('/');
    };

    return {
      cadastrarNovoPet,
      verProximasConsultas,
      navegar,
      sair,
    };
  },
});
</script>

<style scoped>
@import '../assets/styles/HomeStyles.css';
</style>