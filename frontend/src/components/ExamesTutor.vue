<template>
    <div class="container">
      <div class="consultas-cliente">
        <div class="frame6"></div>
        <div class="search-bar">
          <div class="barradepesquisa">
            <input
              type="text"
              class="text10"
              placeholder="procure por paciente, doutor, animal..."
              v-model="searchQuery"
            />
          </div>
          <img
            src="../assets/search11867-foqm.svg"
            alt="Ícone de busca"
            class="search1"
          />
        </div>
        <span class="text11">
          confira aqui todos os exames dos seus bichinhos!
        </span>
        <div class="boto1" @click="examesMarcados">
          <span class="text12">exames marcados</span>
        </div>
        <div class="boto2" @click="examesAnteriores">
          <span class="text13">exames anteriores</span>
        </div>
        <div class="boto3" @click="marcarNovoExame">
          <span class="text14">marcar novo exame</span>
        </div>
  
        <!-- Cards de Exames -->
        <div class="card" v-for="(exame, index) in filteredExames" :key="index">
          <div class="borda1"></div>
          <div class="boto4" @click="verLaudo(exame)">
            <span class="text15">laudo indisponível</span>
          </div>
          <span class="text16">{{ exame.nome }}</span>
          <img :src="exame.imagem" :alt="`Imagem de ${exame.nome}`" class="rectangle1" />
          <span class="text17">{{ exame.tipo }}</span>
          <span class="text18">{{ exame.descricao }}</span>
          <span class="text19">{{ exame.dataHora }}</span>
        </div>
  
        <img
          src="../assets/line24021-73c.svg"
          alt="Linha separadora"
          class="line2"
        />
        <div class="frame31">
          <span class="text42">
            Todos os direitos reservados © Faunora, 2024
          </span>
        </div>
        <span class="text43">exames</span>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, computed } from 'vue';
  import { useRouter } from 'vue-router';
  
  interface Exame {
    nome: string;
    imagem: string;
    tipo: string;
    descricao: string;
    dataHora: string;
  }
  
  export default defineComponent({
    name: 'ExamesTutor',
    setup() {
      const router = useRouter();
  
      // Dados fictícios de exames. Em um cenário real, esses dados viriam de uma API.
      const exames = ref<Exame[]>([
        {
          nome: 'Pongo',
          imagem: '../assets/Rectangle2992.png', // Atualize o caminho conforme necessário
          tipo: 'Canino',
          descricao: 'Hemograma',
          dataHora: '27/10 - 15:30',
        },
        {
          nome: 'Cristal',
          imagem: '../assets/Rectangle3052.png', // Atualize o caminho conforme necessário
          tipo: 'Felino',
          descricao: 'Raio-x',
          dataHora: '02/09 - 16:00',
        },
        {
          nome: 'Lupita',
          imagem: '../assets/Rectangle3053.png', // Atualize o caminho conforme necessário
          tipo: 'Canina',
          descricao: 'Ultrassom',
          dataHora: '19/04 - 08:00',
        },
        // Adicione mais exames conforme necessário
      ]);
  
      const searchQuery = ref('');
  
      const filteredExames = computed(() => {
        if (!searchQuery.value) {
          return exames.value;
        }
        const query = searchQuery.value.toLowerCase();
        return exames.value.filter(
          (exame) =>
            exame.nome.toLowerCase().includes(query) ||
            exame.tipo.toLowerCase().includes(query) ||
            exame.descricao.toLowerCase().includes(query) ||
            exame.dataHora.toLowerCase().includes(query)
        );
      });
  
      const examesMarcados = () => {
        // Lógica para exibir exames marcados
        alert('Exibindo exames marcados...');
        router.push('/exames-marcados'); // Atualize a rota conforme necessário
      };
  
      const examesAnteriores = () => {
        // Lógica para exibir exames anteriores
        alert('Exibindo exames anteriores...');
        router.push('/exames-anteriores'); // Atualize a rota conforme necessário
      };
  
      const marcarNovoExame = () => {
        // Lógica para marcar novo exame
        alert('Redirecionando para marcar novo exame...');
        router.push('/marcar-exame'); // Atualize a rota conforme necessário
      };
  
      const verLaudo = (exame: Exame) => {
        // Lógica para ver laudo
        alert(`Ver laudo de ${exame.nome}`);
        router.push(`/laudo/${exame.nome}`); // Atualize a rota conforme necessário
      };
  
      const sair = () => {
        // Lógica para sair
        alert('Você saiu da conta.');
        router.push('/'); // Atualize a rota conforme necessário
      };
  
      return {
        exames,
        searchQuery,
        filteredExames,
        examesMarcados,
        examesAnteriores,
        marcarNovoExame,
        verLaudo,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/ExamesStyles.css';
  </style>
  