<template>
  <div class="meuspets-container">
    <div class="meuspets-meuspets">
      <!-- Background Overlay -->
      <div class="meuspets-frame6"></div>

      <!-- Barra de Pesquisa -->
      <div class="meuspets-search-bar">
        <div class="meuspets-barradepesquisa">
          <input
            type="text"
            class="meuspets-text10"
            placeholder="Procure por nome, idade, sexo..."
            v-model="searchQuery"
            @input="filterPets"
          />
        </div>
        <img
          src="../assets/search12323-rndnw.svg"
          alt="Ícone de busca"
          class="meuspets-search1"
        />
      </div>

      <!-- Títulos e Descrições -->
      <span class="meuspets-text11">Meus Pets</span>
      <span class="meuspets-text12">
        Confira aqui os perfis de todos os seus pets cadastrados no nosso sistema!
      </span>

      <!-- Botão para abrir o modal -->
      <div class="meuspets-boto10" @click="mostrarModal = true">
        <span class="meuspets-text13">Cadastrar Novo Pet</span>
      </div>

      <!-- Modal para cadastrar pets -->
      <ModalCadastrarPet 
        v-if="mostrarModal" 
        :isVisible="mostrarModal" 
        :nomeTutor="nomeTutor"
        @close="mostrarModal = false"
        @add-pet="adicionarPet"
      />

      <!-- Outros Botões -->
      <div class="meuspets-boto11" @click="verExamesProximos">
        <span class="meuspets-text14">Com Exames Próximos</span>
      </div>
      <div class="meuspets-boto12" @click="verVacinasProximas">
        <span class="meuspets-text15">Com Vacinas Próximas</span>
      </div>

      <!-- Navbar -->
      <div class="meuspets-navbarcliente">
        <img
          src="../assets/line13563-tmpi.svg"
          alt="Line Separator"
          class="meuspets-line1"
        />
        <span class="meuspets-text21" @click="navegar('tutor')">Perfil</span>
        <span class="meuspets-text20" @click="navegar('consultas-tutor')">Consultas</span>
        <span class="meuspets-text19" @click="navegar('vacina-tutor')">Vacinas</span>
        <span class="meuspets-text18" @click="navegar('exames-tutor')">Exames</span>
        <span class="meuspets-text17" @click="navegar('Produtos')">Produtos</span>
        <span class="meuspets-text16" @click="navegar('meus-pets')">Meus Pets</span>
        <div class="meuspets-logo">
          <div class="meuspets-frame20"></div>
          <div class="meuspets-frame24"></div>
          <div class="meuspets-frame21"></div>
          <div class="meuspets-frame23"></div>
          <div class="meuspets-frame22"></div>
        </div>
        <div class="meuspets-botopadrofontemaior">
          <button class="meuspets-boto13" @click="sair">
            <span class="meuspets-text22">SAIR</span>
          </button>
        </div>
      </div>

      <!-- Cards de Pets -->
      <div
        class="meuspets-card"
        v-for="(pet, index) in filteredPets"
        :key="index"
      >
        <span class="meuspets-text23">{{ pet.nome }}</span>
        <img
          :src="pet.imagem"
          :alt="`Imagem de ${pet.nome}`"
          class="meuspets-rectangle"
        />
        <div class="meuspets-botopadro">
          <button class="meuspets-boto" @click="verMais(pet)">
            <span class="meuspets-text">Ver Mais</span>
          </button>
        </div>
        <div class="meuspets-botovazado">
          <button class="meuspets-boto-editar" @click="editarPet(pet)">
            <span class="meuspets-text-editar">Editar</span>
          </button>
        </div>
      </div>

      <!-- Linha Separadora -->
      <img
        src="../assets/line24021-gi6a.svg"
        alt="Line Separator"
        class="meuspets-line2"
      />

      <!-- Rodapé -->
      <div class="meuspets-frame31">
        <span class="meuspets-text35">
          Todos os direitos reservados © Faunora, 2024
        </span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import ModalCadastrarPet from './ModalCadastrarPet.vue';

interface Pet {
  nome: string;
  peso: number;
  especie: string;
  dataNascimento: string;
  idade: number; // Adicionada a propriedade 'idade'
  sexo: string;
  nomeTutor: string;
  imagem: string;
}

export default defineComponent({
  name: 'MeusPets',
  components: {
    ModalCadastrarPet,
  },
  setup() {
    const router = useRouter();
    const mostrarModal = ref(false);
    const nomeTutor = ref('Giovanna Batista'); // Você pode obter isso de um store ou props

    const pets = ref<Pet[]>([
      {
        nome: 'Cristal',
        imagem: '../assets/rectangle3843-eu8-200h.png',
        peso: 3,
        especie: 'Gato',
        dataNascimento: '2019-05-12',
        idade: calcularIdade('2019-05-12'), // Calcular idade
        sexo: 'Fêmea',
        nomeTutor: 'Giovanna Batista',
      },
      {
        nome: 'Pongo',
        imagem: '../assets/rectangle3844-12o-200h.png',
        peso: 2,
        especie: 'Cachorro',
        dataNascimento: '2021-03-22',
        idade: calcularIdade('2021-03-22'), // Calcular idade
        sexo: 'Macho',
        nomeTutor: 'Giovanna Batista',
      },
      {
        nome: 'Picolé',
        imagem: '../assets/rectangle3845-lv2n-200h.png',
        peso: 4,
        especie: 'Hamster',
        dataNascimento: '2018-07-30',
        idade: calcularIdade('2018-07-30'), // Calcular idade
        sexo: 'Fêmea',
        nomeTutor: 'Giovanna Batista',
      },
      {
        nome: 'Angelina',
        imagem: '../assets/rectangle3845-h1wl-200h.png',
        peso: 5,
        especie: 'Cachorro',
        dataNascimento: '2017-11-05',
        idade: calcularIdade('2017-11-05'), // Calcular idade
        sexo: 'Fêmea',
        nomeTutor: 'Giovanna Batista',
      },
    ]);

    // Função para calcular a idade
    function calcularIdade(dataNascimento: string): number {
      const hoje = new Date();
      const nascimento = new Date(dataNascimento);
      let idade = hoje.getFullYear() - nascimento.getFullYear();
      const mes = hoje.getMonth() - nascimento.getMonth();
      if (mes < 0 || (mes === 0 && hoje.getDate() < nascimento.getDate())) {
        idade--;
      }
      return idade;
    }

    const searchQuery = ref('');
    const filteredPets = computed(() => {
      if (!searchQuery.value) {
        return pets.value;
      }
      const query = searchQuery.value.toLowerCase();
      return pets.value.filter(
        (pet) =>
          pet.nome.toLowerCase().includes(query) ||
          pet.idade.toString().includes(query) || // Agora 'idade' existe
          pet.sexo.toLowerCase().includes(query)
      );
    });

    const verMais = (pet: Pet) => {
      alert(`Ver mais detalhes de ${pet.nome}`);
      router.push(`/pet/${pet.nome}`);
    };

    const editarPet = (pet: Pet) => {
      alert(`Editar informações de ${pet.nome}`);
      router.push(`/editar-pet/${pet.nome}`);
    };

    const sair = () => {
      alert('Você saiu da conta.');
      router.push('/');
    };

    const adicionarPet = (novoPet: Pet) => {
      pets.value.push(novoPet);
      alert(`Pet ${novoPet.nome} cadastrado com sucesso!`);
    };

    const navegar = (destino: string) => {
      const rota = `/${destino.toLowerCase().replace(' ', '-')}`;
      router.push(rota);
    };

    const verExamesProximos = () => {
      alert('Redirecionando para exames próximos...');
      router.push('/exames-tutor');
    };

    const verVacinasProximas = () => {
      alert('Redirecionando para vacinas próximas...');
      router.push('/vacinas-tutor');
    };

    const filterPets = () => {
      // O filtro já é computado automaticamente
    };

    return {
      pets,
      searchQuery,
      filteredPets,
      mostrarModal,
      verMais,
      editarPet,
      sair,
      adicionarPet,
      navegar,
      verExamesProximos,
      verVacinasProximas,
      nomeTutor,
      filterPets,
    };
  },
});
</script>


<style scoped>
.meuspets-container {
  width: 100%;
  display: flex;
  overflow: auto;
  min-height: 100vh;
  align-items: center;
  flex-direction: column;
}

.meuspets-meuspets {
  width: 100%;
  min-height: 1252px;
  display: flex;
  overflow: hidden;
  position: relative;
  align-items: flex-start;
  flex-shrink: 0;
  background-color: rgba(255, 255, 255, 1);
}

.meuspets-frame6 {
  top: 136px;
  left: 0px;
  width: 100%;
  height: 556px;
  display: flex;
  overflow: hidden;
  position: absolute;
  align-items: flex-start;
  flex-shrink: 0;
  background-color: rgba(55, 57, 24, 0.46);
}

.meuspets-search-bar {
  top: 595px;
  left: 50em;
  width: 335px;
  height: 40px;
  display: flex;
  position: absolute;
  align-items: center;
  flex-shrink: 0;
}

.meuspets-barradepesquisa {
  gap: 10px;
  width: 335px;
  height: 40px;
  display: flex;
  padding: 12px 40px;
  align-items: center;
  justify-content: center;
  border: 1px solid rgba(84, 86, 47, 1);
  border-radius: 50px;
  background-color: #fff;
}

.meuspets-search1 {
  width: 20px;
  height: 20px;
  margin-left: -30px; /* Ajuste conforme necessário */
}

.meuspets-text10 {
  width: 280px;
  color: rgba(84, 86, 47, 1);
  background-color: transparent;
  font-size: 12px;
  font-weight: 700;
  text-align: left;
}

.meuspets-text11 {
  color: rgba(255, 249, 238, 1);
  text-shadow: 0.5px 0.5px 5px rgba(0, 0, 0, 0.5);
  font-size: 128px;
  font-weight: 700;
  text-align: center;
  position: absolute;
  margin-top: 2.3em;
  left: 50%;
  transform: translateX(-50%);
}

.meuspets-text12 {
  color: rgba(255, 249, 238, 1);
  width: 511px;
  text-shadow: 0.5px 0.5px 3px rgba(0, 0, 0, 0.5);
  font-size: 24px;
  font-weight: 500;
  text-align: center;
  position: absolute;
  margin-top: 18em;
  left: 50%;
  transform: translateX(-50%);
}

.meuspets-boto10,
.meuspets-boto11,
.meuspets-boto12,
.meuspets-boto13 {
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.meuspets-boto10 {
  top: 544px;
  left: 53em;
  width: 239px;
  height: 42px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 40px;
  background-color: rgba(84, 86, 47, 1);
  position: absolute;
}

.meuspets-text13 {
  color: rgba(255, 249, 238, 1);
  font-size: 16px;
  font-weight: 700;
  text-align: center;
}

.meuspets-boto11,
.meuspets-boto12 {
  border: 1px solid rgba(84, 86, 47, 1);
  border-radius: 50px;
  background-color: rgba(84, 86, 47, 1);
  position: absolute;
}

.meuspets-text14,
.meuspets-text15,
.meuspets-text24,
.meuspets-text22,
.meuspets-text23,
.meuspets-text-editar {
  color: rgba(84, 86, 47, 1);
  font-size: 14px;
  font-weight: 700;
  text-align: center;
}

.meuspets-text14,
.meuspets-text15,
.meuspets-text23,
.meuspets-text-editar {
  font-size: 14px;
}

.meuspets-text16,
.meuspets-text17,
.meuspets-text18,
.meuspets-text19,
.meuspets-text20,
.meuspets-text21 {
  color: rgba(67, 67, 67, 1);
  font-size: 24px;
  font-weight: 500;
  text-align: center;
  padding-top: 0.5em;
  margin-left: 7em;
  display: flex;
  cursor: pointer;
}

.meuspets-text16:hover,
.meuspets-text17:hover,
.meuspets-text18:hover,
.meuspets-text19:hover,
.meuspets-text20:hover,
.meuspets-text21:hover {
  text-decoration: underline;
}

.meuspets-navbarcliente {
  top: 0px;
  left: 0px;
  width: 100%;
  height: 136px;
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: space-between;
  background-color: rgba(255, 255, 255, 1);
}

.meuspets-line1 {
  top: 136px;
  left: 0px;
  width: 1440px;
  height: 1px;
  position: absolute;
}

.meuspets-text21,
.meuspets-text20,
.meuspets-text19,
.meuspets-text18,
.meuspets-text17,
.meuspets-text16 {
  /* Já definidos acima */
}

.meuspets-logo {
  padding-top: 1em;
  width: 48px;
  height: 68px;
  display: flex;
  /* position: absolute; */
  align-items: flex-start;
  flex-shrink: 0;
}

.meuspets-frame20,
.meuspets-frame24,
.meuspets-frame21,
.meuspets-frame23,
.meuspets-frame22 {
  position: absolute;
  display: flex;
  align-items: flex-start;
  flex-shrink: 0;
}

.meuspets-frame20 {
  top: 30px;
  left: 30px;
  width: 24px;
  height: 22px;
  border-radius: 100px 0 0 100px;
  background-color: rgba(71, 92, 54, 1);
}

.meuspets-frame24 {
  top: 30px;
  left: 54px;
  width: 24px;
  height: 22px;
  border-radius: 0 100px 100px 0;
  background-color: rgba(146, 151, 129, 1);
}

.meuspets-frame21 {
  top: 52px;
  left: 30px;
  width: 24px;
  height: 24px;
  border-radius: 100px 0 0 100px;
  background-color: rgba(151, 166, 126, 1);
}

.meuspets-frame23 {
  top: 52px;
  left: 54px;
  width: 24px;
  height: 24px;
  border-radius: 100px;
  background-color: rgba(74, 94, 56, 1);
}

.meuspets-frame22 {
  top: 76px;
  left: 30px;
  width: 24px;
  height: 22px;
  border-radius: 100px 0 100px 100px;
  background-color: rgba(55, 70, 40, 1);
}

.meuspets-botopadrofontemaior {
  margin-top: 0.5em;
  margin-right: 10em;
  width: 89px;
  height: 53px;
  display: flex;
  align-items: center;
  flex-shrink: 0;
}

.meuspets-boto13 {
  width: 89px;
  height: 53px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 40px;
  background-color: rgba(84, 86, 47, 1);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.meuspets-text22 {
  color: rgba(255, 249, 238, 1);
  font-size: 22px;
  font-weight: 700;
  text-align: center;
}

.meuspets-card {
  width: 170px;
  height: 268px;
  margin-top: 53em;
  display: flex;
  position: relative;
  padding: 5em;
  margin-left: 14em;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.meuspets-text23 {
  margin-top: 4em;
  color: rgba(84, 86, 47, 1);
  font-size: 24px;
  font-weight: 700;
  text-align: center;
}

.meuspets-rectangle {
  top: 0px;
  left: 0px;
  width: 170px;
  height: 170px;
  position: absolute;
  border: 1px solid rgba(55, 57, 25, 1);
  border-radius: 200px;
}

.meuspets-botopadro {
  top: 200px;
  left: 20px;
  width: 129px;
  height: 22px;
  display: flex;
  position: absolute;
  align-items: center;
  flex-shrink: 0;
}

.meuspets-boto {
  width: 129px;
  height: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 40px;
  background-color: rgba(84, 86, 47, 1);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.meuspets-text {
  color: rgba(255, 249, 238, 1);
  font-size: 14px;
  font-weight: 700;
  text-align: center;
}

.meuspets-botovazado {
  top: 230px;
  left: 20px;
  width: 129px;
  height: 22px;
  display: flex;
  position: absolute;
  align-items: center;
  flex-shrink: 0;
}

.meuspets-boto-editar {
  width: 129px;
  height: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid rgba(84, 86, 47, 1);
  border-radius: 40px;
  background-color: rgba(255, 255, 255, 1);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.meuspets-text-editar {
  color: rgba(84, 86, 47, 1);
  font-size: 14px;
  font-weight: 700;
  text-align: center;
}

.meuspets-line2 {
  top: 1152px;
  left: 0px;
  width: 1440px;
  height: 1px;
  position: absolute;
}

.meuspets-frame31 {
  top: 1152px;
  left: 0px;
  width: 100%;
  height: 100px;
  display: flex;
  overflow: hidden;
  border-top: 0.3px solid #7b7b7b;
  position: absolute;
  align-items: flex-start;
  flex-shrink: 0;
  background-color: rgba(255, 255, 255, 1);
}

.meuspets-text35 {
  color: rgba(163, 164, 149, 1);
  font-size: 24px;
  font-weight: 500;
  text-align: left;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 35px;
}

/* Hover Effects */
.meuspets-boto10:hover,
.meuspets-boto13:hover,
.meuspets-boto11:hover,
.meuspets-boto12:hover {
  background-color: #373919;
}

.meuspets-boto11:hover,
.meuspets-boto12:hover {
  background-color: rgba(84, 86, 47, 1);
}

.meuspets-text18:hover {
  text-decoration: underline;
}

/* Responsividade */
@media (max-width: 768px) {
  .meuspets-container {
    width: 100%;
    padding: 20px;
  }

  .meuspets-meuspets {
    flex-direction: column;
    height: auto;
    align-items: center;
  }

  .meuspets-frame6 {
    width: 100%;
    height: auto;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .meuspets-search-bar {
    width: 100%;
    left: 0px;
    top: 20px;
  }

  .meuspets-barradepesquisa {
    width: 100%;
    padding: 12px 20px;
  }

  .meuspets-search1 {
    left: 280px; /* Ajuste conforme necessário */
  }

  .meuspets-text11,
  .meuspets-text12 {
    width: 90%;
    left: 5%;
  }

  .meuspets-boto10,
  .meuspets-boto11,
  .meuspets-boto12,
  .meuspets-boto13 {
    left: 50%;
    transform: translateX(-50%);
  }

  .meuspets-navbarcliente {
    width: 100%;
    height: auto;
    flex-direction: column;
    align-items: center;
  }

  .meuspets-text21,
  .meuspets-text20,
  .meuspets-text19,
  .meuspets-text18,
  .meuspets-text17,
  .meuspets-text16 {
    transform: translateX(-50%);
    margin-bottom: 10px;
  }

  .meuspets-logo {
    left: 50%;
    transform: translateX(-50%);
    margin-top: 10px;
  }

  .meuspets-botopadrofontemaior {
    left: 50%;
    transform: translateX(-50%);
  }

  .meuspets-card {
    position: relative;
    top: auto;
    left: auto;
    margin: 20px;
  }

  .meuspets-frame31 {
    width: 100%;
    height: auto;
    top: auto;
    left: auto;
    border-top: 0.3px solid #7b7b7b;
  }

  .meuspets-text35 {
    left: 50%;
    transform: translateX(-50%);
  }
}
</style>
