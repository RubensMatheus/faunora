<template>
    <div class="container">
      <div class="homepagecliente">
        <div class="frame6">
          <div class="boto1" @click="cadastrarNovoPet">
            <span class="text10">cadastrar novo pet</span>
          </div>
        </div>
        <span class="text11-vet">bem-vindo(a) ao faunora, maria!</span>
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
          <span class="text25" @click="navegar('Perfil')">Perfil</span>
          <span class="text26" @click="navegar('Consultas')">Consultas</span>
          <span class="text27" @click="navegar('Vacinas')">Vacinas</span>
          <span class="text28" @click="navegar('Exames')">Exames</span>
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
    name: 'HomeVet',
    setup() {
      const router = useRouter();
  
      const cadastrarNovoPet = () => {
        // Lógica para cadastrar novo pet
        alert('Redirecionando para cadastrar novo pet...');
        router.push('/cadastrar-pet'); // Atualize a rota conforme necessário
      };
  
      const verProximasConsultas = () => {
        // Lógica para ver próximas consultas
        alert('Redirecionando para ver próximas consultas...');
        router.push('/proximas-consultas'); // Atualize a rota conforme necessário
      };
  
      const navegar = (destino: string) => {
        // Lógica para navegar entre seções
        alert(`Navegando para ${destino}...`);
        // Exemplo de rota: `/perfil`, `/consultas`, etc.
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
  