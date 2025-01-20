<template>
    <div class="vacina-container">
      <div class="consultas-cliente">
        <div class="frame6"></div>
        
        <!-- Barra de Pesquisa -->
        <div class="search-bar-vet">
          <div class="barradepesquisa">
            <!-- Campo de Pesquisa -->
            <input 
              class="text10" 
              placeholder="procure por paciente, animal, tutor..."
              v-model="searchQuery"
            />
            <img
              src="../assets/search11867-foqm.svg"
              alt="Ícone de busca"
              class="search1"
            />
          </div>
        </div>
        
        <!-- Título -->
        <span class="text11-vet">
          confira aqui todas as consultas pelas quais você é/foi responsável!
        </span>
        
        <!-- Botões -->
        <div class="boto1" @click="verConsultasMarcadas">
          <span class="text12">consultas marcadas</span>
        </div>
        <div class="boto2" @click="verConsultasAnteriores">
          <span class="text13">consultas anteriores</span>
        </div>
        
        <!-- Cards de Consultas -->
        <div 
          class="card1" 
          v-for="(consulta, index) in filteredVacinas" 
          :key="index"
        >
          <div class="borda1"></div>
          <span class="text16">{{ consulta.nome }}</span>
          <img 
            :src="consulta.imagem" 
            :alt="`Imagem de ${consulta.nome}`" 
            class="rectangle1" 
          />
          <span class="text17">{{ consulta.tipo }}</span>
          <span class="text18">{{ consulta.descricao }}</span>
          <span class="text19">{{ consulta.dataHora }}</span>
        </div>
        
        <div 
          class="card2" 
          v-for="(consulta, index) in filteredVacinas" 
          :key="index + 100"
        >
          <div class="borda2"></div>
          <span class="text21">{{ consulta.nome }}</span>
          <img 
            :src="consulta.imagem" 
            :alt="`Imagem de ${consulta.nome}`" 
            class="rectangle2" 
          />
          <span class="text22">{{ consulta.tipo }}</span>
          <span class="text23">{{ consulta.descricao }}</span>
          <span class="text24">{{ consulta.dataHora }}</span>
        </div>
        
        <div 
          class="card3" 
          v-for="(consulta, index) in filteredVacinas" 
          :key="index + 200"
        >
          <div class="borda3"></div>
          <span class="text26">{{ consulta.nome }}</span>
          <img 
            :src="consulta.imagem" 
            :alt="`Imagem de ${consulta.nome}`" 
            class="rectangle3" 
          />
          <span class="text27">{{ consulta.tipo }}</span>
          <span class="text28">{{ consulta.descricao }}</span>
          <span class="text29">{{ consulta.dataHora }}</span>
        </div>
        
        <div 
          class="card4" 
          v-for="(consulta, index) in filteredVacinas" 
          :key="index + 300"
        >
          <div class="borda4"></div>
          <span class="text31">{{ consulta.nome }}</span>
          <img 
            :src="consulta.imagem" 
            :alt="`Imagem de ${consulta.nome}`" 
            class="rectangle4" 
          />
          <span class="text32">{{ consulta.tipo }}</span>
          <span class="text33">{{ consulta.descricao }}</span>
          <span class="text34">{{ consulta.dataHora }}</span>
        </div>
        
        <!-- Navbar -->
        <div class="navbarcliente">
          <img
            src="../assets/line13564-idz6.svg"
            alt="Linha separadora"
            class="line1"
          />
          <span class="text35" @click="navegar('Perfil')">Perfil</span>
          <span class="text36" @click="navegar('Consultas')">Consultas</span>
          <span class="text37" @click="navegar('Vacinas')">Vacinas</span>
          <span class="text38" @click="navegar('Exames')">Exames</span>
          <span class="text39" @click="navegar('Produtos')">Produtos</span>
          <div class="logo">
            <div class="frame20"></div>
            <div class="frame24"></div>
            <div class="frame21"></div>
            <div class="frame23"></div>
            <div class="frame22"></div>
          </div>
          <div class="botopadrofontemaior">
            <div class="boto8" @click="sair">
              <span class="text41">SAIR</span>
            </div>
          </div>
        </div>
        
        <!-- Linha Separadora -->
        <img
          src="../assets/line24021-73c.svg"
          alt="Linha separadora"
          class="line2"
        />
        
        <!-- Rodapé -->
        <div class="frame31">
          <span class="text42">
            Todos os direitos reservados © Faunora, 2024
          </span>
        </div>
        
        <!-- Título da Página -->
        <span class="text43-vet">consultas</span>
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
    descricao: string;
    dataHora: string;
  }
  
  export default defineComponent({
    name: 'VacinaVet',
    setup() {
      const router = useRouter();
  
      // Dados fictícios de consultas. Em um cenário real, esses dados viriam de uma API.
      const vacinas = ref<Consulta[]>([
        {
          nome: 'Pongo',
          imagem: '../assets/Rectangle2992.png', // Atualize o caminho conforme necessário
          tipo: 'Canino',
          descricao: 'Tutor Jade',
          dataHora: '27/10 - 15:30',
        },
        {
          nome: 'Pongo',
          imagem: '../assets/Rectangle3052.png',
          tipo: 'Canino',
          descricao: 'Tutor Levi',
          dataHora: '21/10 - 10:30',
        },
        {
          nome: 'Cristal',
          imagem: '../assets/Rectangle3052.png',
          tipo: 'Felino',
          descricao: 'Tutor Rafael',
          dataHora: '02/09 - 16:00',
        },
        {
          nome: 'Lupita',
          imagem: '../assets/Rectangle3053.png',
          tipo: 'Canina',
          descricao: 'Tutor Jade',
          dataHora: '19/04 - 08:00',
        },
        // Adicione mais consultas conforme necessário
      ]);
  
      const searchQuery = ref('');
  
      const filteredVacinas = computed(() => {
        if (!searchQuery.value) {
          return vacinas.value;
        }
        const query = searchQuery.value.toLowerCase();
        return vacinas.value.filter(
          (consulta) =>
            consulta.nome.toLowerCase().includes(query) ||
            consulta.tipo.toLowerCase().includes(query) ||
            consulta.descricao.toLowerCase().includes(query) ||
            consulta.dataHora.toLowerCase().includes(query)
        );
      });
  
      const verConsultasMarcadas = () => {
        // Lógica para ver consultas marcadas
        alert('Redirecionando para ver consultas marcadas...');
        router.push('/consultas-marcadas'); // Atualize a rota conforme necessário
      };
  
      const verConsultasAnteriores = () => {
        // Lógica para ver consultas anteriores
        alert('Redirecionando para ver consultas anteriores...');
        router.push('/consultas-anteriores'); // Atualize a rota conforme necessário
      };
  
      const marcarNovaDosagem = () => {
        // Lógica para marcar nova dosagem
        alert('Redirecionando para marcar nova dosagem...');
        router.push('/marcar-dosagem'); // Atualize a rota conforme necessário
      };
  
      const navegar = (destino: string) => {
        // Lógica para navegar entre seções
        alert(`Navegando para ${destino}...`);
        const rota = `/${destino.toLowerCase().replace(' ', '-')}`;
        router.push(rota);
      };
  
      const sair = () => {
        // Lógica para sair
        alert('Você saiu da conta.');
        router.push('/'); // Atualize a rota conforme necessário
      };
  
      return {
        vacinas,
        searchQuery,
        filteredVacinas,
        verConsultasMarcadas,
        verConsultasAnteriores,
        marcarNovaDosagem,
        navegar,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/VacinaStyles.css';
  </style>
  