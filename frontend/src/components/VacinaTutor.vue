<template>
    <div class="vacina-container">
      <div class="consultas-cliente">
        <div class="frame6"></div>
        
        <!-- Barra de Pesquisa -->
        <div class="search-bar">
          <div class="barradepesquisa">
            <!-- Campo de Pesquisa -->
            <input 
              class="text10" 
              placeholder="procure por paciente, doutor, animal..."
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
        <span class="text11">
          confira aqui todos as vacinas dos seus bichinhos!
        </span>
        
        <!-- Botões -->
        <div class="boto1" @click="verDosagensMarcadas">
          <span class="text12">dosagens marcadas</span>
        </div>
        <div class="boto2" @click="verDosagensAnteriores">
          <span class="text13">dosagens anteriores</span>
        </div>
        <div class="boto3" @click="marcarNovaDosagem">
          <span class="text14">marcar nova dosagem</span>
        </div>
        
        <!-- Cards de Vacinas -->
        <div 
          class="card1" 
          v-for="(vacina, index) in filteredVacinas" 
          :key="index"
        >
          <div class="borda1"></div>
          <div class="boto4" @click="opcaoIndisponivel">
            <span class="text15">opção indisponível</span>
          </div>
          <span class="text16">{{ vacina.nome }}</span>
          <img 
            :src="vacina.imagem" 
            :alt="`Imagem de ${vacina.nome}`" 
            class="rectangle1" 
          />
          <span class="text17">{{ vacina.tipo }}</span>
          <span class="text18">{{ vacina.descricao }}</span>
          <span class="text19">{{ vacina.dataHora }}</span>
        </div>
        
        <div 
          class="card2" 
          v-for="(vacina, index) in filteredVacinas" 
          :key="index + 100"
        >
          <div class="borda2"></div>
          <div class="boto5" @click="marcarNovaDosagem">
            <span class="text20">marcar nova dosagem</span>
          </div>
          <span class="text21">{{ vacina.nome }}</span>
          <img 
            :src="vacina.imagem" 
            :alt="`Imagem de ${vacina.nome}`" 
            class="rectangle2" 
          />
          <span class="text22">{{ vacina.tipo }}</span>
          <span class="text23">{{ vacina.descricao }}</span>
          <span class="text24">{{ vacina.dataHora }}</span>
        </div>
        
        <div 
          class="card3" 
          v-for="(vacina, index) in filteredVacinas" 
          :key="index + 200"
        >
          <div class="borda3"></div>
          <div class="boto6" @click="marcarNovaDosagem">
            <span class="text25">marcar nova dosagem</span>
          </div>
          <span class="text26">{{ vacina.nome }}</span>
          <img 
            :src="vacina.imagem" 
            :alt="`Imagem de ${vacina.nome}`" 
            class="rectangle3" 
          />
          <span class="text27">{{ vacina.tipo }}</span>
          <span class="text28">{{ vacina.descricao }}</span>
          <span class="text29">{{ vacina.dataHora }}</span>
        </div>
        
        <div 
          class="card4" 
          v-for="(vacina, index) in filteredVacinas" 
          :key="index + 300"
        >
          <div class="borda4"></div>
          <div class="boto7" @click="marcarNovaDosagem">
            <span class="text30">marcar nova dosagem</span>
          </div>
          <span class="text31">{{ vacina.nome }}</span>
          <img 
            :src="vacina.imagem" 
            :alt="`Imagem de ${vacina.nome}`" 
            class="rectangle4" 
          />
          <span class="text32">{{ vacina.tipo }}</span>
          <span class="text33">{{ vacina.descricao }}</span>
          <span class="text34">{{ vacina.dataHora }}</span>
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
        <span class="text43">vacinas</span>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, computed } from 'vue';
  import { useRouter } from 'vue-router';
  
  interface Vacina {
    nome: string;
    imagem: string;
    tipo: string;
    descricao: string;
    dataHora: string;
  }
  
  export default defineComponent({
    name: 'VacinaTutor',
    setup() {
      const router = useRouter();
  
      // Dados fictícios de vacinas. Em um cenário real, esses dados viriam de uma API.
      const vacinas = ref<Vacina[]>([
        {
          nome: 'Pongo',
          imagem: '../assets/Rectangle2992.png', // Atualize o caminho conforme necessário
          tipo: 'Canino',
          descricao: 'Quíntupla',
          dataHora: '27/10 - 15:30',
        },
        {
          nome: 'Pongo',
          imagem: '../assets/Rectangle3052.png',
          tipo: 'Canino',
          descricao: 'Quíntupla',
          dataHora: '21/10 - 10:30',
        },
        {
          nome: 'Cristal',
          imagem: '../assets/Rectangle3052.png',
          tipo: 'Felino',
          descricao: 'Giardíase',
          dataHora: '02/09 - 16:00',
        },
        {
          nome: 'Lupita',
          imagem: '../assets/Rectangle3053.png',
          tipo: 'Canina',
          descricao: 'Antirrábica',
          dataHora: '19/04 - 08:00',
        },
        // Adicione mais vacinas conforme necessário
      ]);
  
      const searchQuery = ref('');
  
      const filteredVacinas = computed(() => {
        if (!searchQuery.value) {
          return vacinas.value;
        }
        const query = searchQuery.value.toLowerCase();
        return vacinas.value.filter(
          (vacina) =>
            vacina.nome.toLowerCase().includes(query) ||
            vacina.tipo.toLowerCase().includes(query) ||
            vacina.descricao.toLowerCase().includes(query) ||
            vacina.dataHora.toLowerCase().includes(query)
        );
      });
  
      const verDosagensMarcadas = () => {
        // Lógica para ver dosagens marcadas
        alert('Redirecionando para ver dosagens marcadas...');
        router.push('/dosagens-marcadas'); // Atualize a rota conforme necessário
      };
  
      const verDosagensAnteriores = () => {
        // Lógica para ver dosagens anteriores
        alert('Redirecionando para ver dosagens anteriores...');
        router.push('/dosagens-anteriores'); // Atualize a rota conforme necessário
      };
  
      const marcarNovaDosagem = () => {
        // Lógica para marcar nova dosagem
        alert('Redirecionando para marcar nova dosagem...');
        router.push('/marcar-dosagem'); // Atualize a rota conforme necessário
      };
  
      const opcaoIndisponivel = () => {
        // Lógica para opção indisponível
        alert('Esta opção está indisponível no momento.');
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
        verDosagensMarcadas,
        verDosagensAnteriores,
        marcarNovaDosagem,
        opcaoIndisponivel,
        navegar,
        sair,
      };
    },
  });
  </script>
  
  <style scoped>
  @import '../assets/styles/VacinaStyles.css';
  </style>
  