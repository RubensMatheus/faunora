<template>
    <div class="container">
      <div class="consultas-cliente">
        <div class="frame6"></div>
        <div class="search-bar-vet">
          <div class="barradepesquisa">
            <input
              type="text"
              class="text10"
              placeholder="procure por paciente, animal, tutor..."
              v-model="searchQuery"
            />
          </div>
          <img
            src="../assets/search11867-foqm.svg"
            alt="Ícone de busca"
            class="search1"
          />
        </div>
        <span class="text11-vet">
          confira aqui todos os exames pelos quais você é/foi responsável!
        </span>
        <div class="boto1" @click="examesMarcados">
          <span class="text12">exames marcados</span>
        </div>
        <div class="boto2" @click="examesMarcados">
          <span class="text13">exames marcados</span>
        </div>
        <div class="boto3" @click="marcarNovoExame">
          <span class="text14">marcar novo exame</span>
        </div>
  
        <!-- Cards de Exames -->
        <div class="card" v-for="(exame, index) in filteredExames" :key="index">
          <div class="borda2"></div>
          <div class="boto5" @click="cadastrarLaudo(exame)">
            <span class="text44">cadastrar laudo</span>
          </div>
          <span class="text21">{{ exame.nome }}</span>
          <img :src="exame.imagem" :alt="`Imagem de ${exame.nome}`" class="rectangle2" />
          <span class="text22">{{ exame.tipo }}</span>
          <span class="text23">{{ exame.descricao }}</span>
          <span class="text24">{{ exame.dataHora }}</span>
        </div>
  
        <div class="card" v-for="(exame, index) in filteredExames" :key="index + 100">
          <div class="borda3"></div>
          <div class="boto6" @click="cadastrarLaudo(exame)">
            <span class="text45">cadastrar laudo</span>
          </div>
          <span class="text26">{{ exame.nome }}</span>
          <img :src="exame.imagem" :alt="`Imagem de ${exame.nome}`" class="rectangle3" />
          <span class="text27">{{ exame.tipo }}</span>
          <span class="text28">{{ exame.descricao }}</span>
          <span class="text29">{{ exame.dataHora }}</span>
        </div>
  
        <div class="card" v-for="(exame, index) in filteredExames" :key="index + 200">
          <div class="borda4"></div>
          <div class="boto7" @click="cadastrarLaudo(exame)">
            <span class="text46">cadastrar laudo</span>
          </div>
          <span class="text31">{{ exame.nome }}</span>
          <img :src="exame.imagem" :alt="`Imagem de ${exame.nome}`" class="rectangle4" />
          <span class="text32">{{ exame.tipo }}</span>
          <span class="text33">{{ exame.descricao }}</span>
          <span class="text34">{{ exame.dataHora }}</span>
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
        <span class="text43-vet">exames</span>
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
    name: 'ExamesVet',
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
          descricao: 'Ultrassom',
          dataHora: '02/09 - 16:00',
        },
        {
          nome: 'Lupita',
          imagem: '../assets/Rectangle3053.png', // Atualize o caminho conforme necessário
          tipo: 'Canina',
          descricao: 'Endoscopia',
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
  
      const cadastrarLaudo = (exame: Exame) => {
        // Lógica para cadastrar laudo
        alert(`Cadastrando laudo para ${exame.nome}`);
        router.push(`/cadastrar-laudo/${exame.nome}`); // Atualize a rota conforme necessário
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
        cadastrarLaudo,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/ExamesStyles.css';
  </style>
  