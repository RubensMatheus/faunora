<template>
    <div class="container">
      <div class="consultas-cliente">
        <div class="frame6"></div>
        <div class="search-bar">
          <div class="barradepesquisa">
            <input
              type="text"
              class="text10"
              placeholder="Procure por paciente, doutor, animal..."
              v-model="searchQuery"
            />
          </div>
          <img
            src="../assets/search11867-foqm.svg"
            alt="Ícone de busca"
            class="search1"
          />
        </div>
        <span class="text11">Confira aqui todas as consultas dos seus bichinhos!</span>
        <div class="boto1" @click="consultasMarcadas">
          <span class="text12">Consultas Marcadas</span>
        </div>
        <div class="boto2" @click="consultasAnteriores">
          <span class="text13">Consultas Anteriores</span>
        </div>
        <div class="boto3" @click="marcarNovaConsulta">
          <span class="text14">Marcar Nova Consulta</span>
        </div>
  
        <!-- Cards de Consultas -->
        <div
          class="card"
          v-for="(consulta, index) in filteredConsultas"
          :key="index"
        >
          <span class="text16">{{ consulta.nome }}</span>
          <img
            :src="consulta.imagem"
            :alt="`Imagem de ${consulta.nome}`"
            class="rectangle"
          />
          <span class="text17">{{ consulta.tipo }}</span>
          <span class="text18">{{ consulta.doutor }}</span>
          <span class="text19">{{ consulta.dataHora }}</span>
          <div class="boto4" @click="verReceita(consulta)">
            <span class="text15">Receita Indisponível</span>
          </div>
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
        <span class="text43">Consultas</span>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, computed } from 'vue';
  import { useRouter } from 'vue-router';
  
  interface Consulta {
    nome: string;
    imagem: string;
    tipo: string;
    doutor: string;
    dataHora: string;
  }
  
  export default defineComponent({
    name: 'ConsultasTutor',
    setup() {
      const router = useRouter();
  
      // Dados fictícios de consultas. Em um cenário real, esses dados viriam de uma API.
      const consultas = ref<Consulta[]>([
        {
          nome: 'Pongo',
          imagem: '../assets/rectangle2992.png', // Atualize o caminho conforme necessário
          tipo: 'Canino',
          doutor: 'Doutora Jade',
          dataHora: '27/10 - 15:30',
        },
        {
          nome: 'Cristal',
          imagem: '../assets/rectangle3052.png', // Atualize o caminho conforme necessário
          tipo: 'Felino',
          doutor: 'Doutor Rafael',
          dataHora: '02/09 - 16:00',
        },
        // Adicione mais consultas conforme necessário
      ]);
  
      const searchQuery = ref('');
  
      const filteredConsultas = computed(() => {
        if (!searchQuery.value) {
          return consultas.value;
        }
        const query = searchQuery.value.toLowerCase();
        return consultas.value.filter(
          (consulta) =>
            consulta.nome.toLowerCase().includes(query) ||
            consulta.tipo.toLowerCase().includes(query) ||
            consulta.doutor.toLowerCase().includes(query) ||
            consulta.dataHora.toLowerCase().includes(query)
        );
      });
  
      const consultasMarcadas = () => {
        // Lógica para exibir consultas marcadas
        alert('Exibindo consultas marcadas...');
        router.push('/consultas-marcadas'); // Atualize a rota conforme necessário
      };
  
      const consultasAnteriores = () => {
        // Lógica para exibir consultas anteriores
        alert('Exibindo consultas anteriores...');
        router.push('/consultas-anteriores'); // Atualize a rota conforme necessário
      };
  
      const marcarNovaConsulta = () => {
        // Lógica para marcar nova consulta
        alert('Redirecionando para marcar nova consulta...');
        router.push('/marcar-consulta'); // Atualize a rota conforme necessário
      };
  
      const verReceita = (consulta: Consulta) => {
        // Lógica para ver receita
        alert(`Ver receita de ${consulta.nome}`);
        router.push(`/receita/${consulta.nome}`); // Atualize a rota conforme necessário
      };
  
      const sair = () => {
        // Lógica para sair
        alert('Você saiu da conta.');
        router.push('/'); // Atualize a rota conforme necessário
      };
  
      return {
        consultas,
        searchQuery,
        filteredConsultas,
        consultasMarcadas,
        consultasAnteriores,
        marcarNovaConsulta,
        verReceita,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/ConsultasStyles.css';
  </style>
  