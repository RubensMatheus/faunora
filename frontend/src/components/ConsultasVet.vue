<template>
    <div class="container">
      <div class="consultas-cliente">
        <div class="frame6"></div>
        <div class="search-bar-vet">
          <div class="barradepesquisa">
            <input
              type="text"
              class="text10"
              placeholder="Procure por paciente, animal, tutor..."
              v-model="searchQuery"
            />
          </div>
          <img
            src="../assets/search11867-foqm.svg"
            alt="Ícone de busca"
            class="search1"
          />
        </div>
        <span class="text11-vet">Confira aqui todas as consultas pelas quais você é/foi responsável!</span>
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
          <span class="text18">{{ consulta.tutor }}</span>
          <span class="text19">{{ consulta.dataHora }}</span>
          <div class="boto4" @click="cadastrarReceita(consulta)">
            <span class="text44">Cadastrar Receita</span>
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
        <span class="text43-vet">Consultas</span>
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
    tutor: string;
    dataHora: string;
  }
  
  export default defineComponent({
    name: 'ConsultasVet',
    setup() {
      const router = useRouter();
  
      // Dados fictícios de consultas. Em um cenário real, esses dados viriam de uma API.
      const consultas = ref<Consulta[]>([
        {
          nome: 'Pongo',
          imagem: '../assets/rectangle2992.png', // Atualize o caminho conforme necessário
          tipo: 'Canino',
          tutor: 'Tutor Jade',
          dataHora: '27/10 - 15:30',
        },
        {
          nome: 'Cristal',
          imagem: '../assets/rectangle3052.png', // Atualize o caminho conforme necessário
          tipo: 'Felino',
          tutor: 'Tutor Rafael',
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
            consulta.tutor.toLowerCase().includes(query) ||
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
  
      const cadastrarReceita = (consulta: Consulta) => {
        // Lógica para cadastrar receita
        alert(`Cadastrando receita para ${consulta.nome}`);
        router.push(`/cadastrar-receita/${consulta.nome}`); // Atualize a rota conforme necessário
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
        cadastrarReceita,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/ConsultasStyles.css';
  </style>
  