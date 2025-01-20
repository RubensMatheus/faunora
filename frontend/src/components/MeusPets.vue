<template>
    <div class="meuspets-container">
      <div class="meuspets-meuspets">
        <div class="meuspets-frame6"></div>
        <div class="meuspets-search-bar">
          <div class="meuspets-barradepesquisa">
            <input
              type="text"
              class="meuspets-text10"
              placeholder="Procure por nome, idade, sexo..."
              v-model="searchQuery"
            />
          </div>
          <img
            src="../assets/search12323-rndnw.svg"
            alt="Search Icon"
            class="meuspets-search1"
          />
        </div>
        <span class="meuspets-text11">Meu Pets</span>
        <span class="meuspets-text12">
          Confira aqui os perfis de todos os seus pets cadastrados no nosso sistema!
        </span>
        <div class="meuspets-boto10" @click="cadastrarNovoPet">
          <span class="meuspets-text13">Cadastrar Novo Pet</span>
        </div>
        <div class="meuspets-boto11" @click="verExamesProximos">
          <span class="meuspets-text14">Com Exames Próximos</span>
        </div>
        <div class="meuspets-boto12" @click="verVacinasProximas">
          <span class="meuspets-text15">Com Vacinas Próximas</span>
        </div>
        <div class="meuspets-navbarcliente">
          <img
            src="../assets/line13563-tmpi.svg"
            alt="Line Separator"
            class="meuspets-line1"
          />
          <span class="meuspets-text16">Meus Pets</span>
          <span class="meuspets-text17">Perfil</span>
          <span class="meuspets-text18">Consultas</span>
          <span class="meuspets-text19">Vacinas</span>
          <span class="meuspets-text20">Exames</span>
          <span class="meuspets-text21">Produtos</span>
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
        <img
          src="../assets/line24021-gi6a.svg"
          alt="Line Separator"
          class="meuspets-line2"
        />
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
  
  interface Pet {
    nome: string;
    imagem: string;
    idade: number;
    sexo: string;
  }
  
  export default defineComponent({
    name: 'MeusPets',
    setup() {
      const router = useRouter();
  
      // Dados fictícios de pets. Em um cenário real, esses dados viriam de uma API.
      const pets = ref<Pet[]>([
        {
          nome: 'Cristal',
          imagem: '../assets/rectangle3843-eu8-200h.png',
          idade: 3,
          sexo: 'Fêmea',
        },
        {
          nome: 'Pongo',
          imagem: '../assets/rectangle3844-12o-200h.png',
          idade: 2,
          sexo: 'Macho',
        },
        {
          nome: 'Picolé',
          imagem: '../assets/rectangle3845-lv2n-200h.png',
          idade: 4,
          sexo: 'Fêmea',
        },
        {
          nome: 'Angelina',
          imagem: '../assets/rectangle3845-h1wl-200h.png',
          idade: 5,
          sexo: 'Fêmea',
        },
      ]);
  
      const searchQuery = ref('');
  
      const filteredPets = computed(() => {
        if (!searchQuery.value) {
          return pets.value;
        }
        const query = searchQuery.value.toLowerCase();
        return pets.value.filter(
          (pet) =>
            pet.nome.toLowerCase().includes(query) ||
            pet.idade.toString().includes(query) ||
            pet.sexo.toLowerCase().includes(query)
        );
      });
  
      const cadastrarNovoPet = () => {
        // Lógica para cadastrar um novo pet
        alert('Redirecionando para a página de cadastro de novo pet...');
        router.push('/cadastrar-pet');
      };
  
      const verExamesProximos = () => {
        // Lógica para ver exames próximos
        alert('Redirecionando para exames próximos...');
        router.push('/exames-proximos');
      };
  
      const verVacinasProximas = () => {
        // Lógica para ver vacinas próximas
        alert('Redirecionando para vacinas próximas...');
        router.push('/vacinas-proximas');
      };
  
      const verMais = (pet: Pet) => {
        // Lógica para ver mais detalhes do pet
        alert(`Ver mais detalhes de ${pet.nome}`);
        router.push(`/pet/${pet.nome}`);
      };
  
      const editarPet = (pet: Pet) => {
        // Lógica para editar o pet
        alert(`Editar informações de ${pet.nome}`);
        router.push(`/editar-pet/${pet.nome}`);
      };
  
      const sair = () => {
        // Lógica para sair
        alert('Você saiu da conta.');
        router.push('/');
      };
  
      return {
        pets,
        searchQuery,
        filteredPets,
        cadastrarNovoPet,
        verExamesProximos,
        verVacinasProximas,
        verMais,
        editarPet,
        sair,
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
    top: 495px;
    left: 553px;
    width: 335px;
    height: 40px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .meuspets-barradepesquisa {
    gap: 10px;
    top: 0px;
    left: 0px;
    width: 335px;
    height: 40px;
    display: flex;
    padding: 12px 40px;
    position: absolute;
    align-items: center;
    justify-content: center;
    border-color: rgba(84, 86, 47, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 50px;
    background-color: #fff; /* Adicionado para melhor visibilidade */
  }
  
  .meuspets-search1 {
    top: 10px;
    left: 300px;
    width: 20px;
    height: 20px;
    position: absolute;
  }
  
  .meuspets-text10 {
    width: 280px;
    color: rgba(84, 86, 47, 1);
    background-color: transparent;
    height: auto;
    position: relative; /* Alterado de absolute para relative */
    font-size: 12px;
    font-weight: 700;
    text-align: left;
  }
  
  .meuspets-text11 {
    top: 242px;
    left: 0px;
    color: rgba(255, 249, 238, 1);
    right: 0px;
    width: 1046px;
    text-shadow: 0.5px 0.5px 5px rgba(0, 0, 0, 0.5);
    height: auto;
    margin: auto;
    position: absolute;
    font-size: 128px;
    font-weight: 700;
    text-align: center;
  }
  
  .meuspets-text12 {
    top: 377px;
    left: 465px;
    color: rgba(255, 249, 238, 1);
    width: auto;
    height: auto;
    position: absolute;
    text-shadow: 0.5px 0.5px 3px rgba(0, 0, 0, 0.5);
    font-size: 24px;
    font-weight: 500;
    text-align: center;
  }
  
  .meuspets-boto10,
  .meuspets-boto11,
  .meuspets-boto12,
  .meuspets-boto13,
  .meuspets-boto14,
  .meuspets-boto15,
  .meuspets-boto16,
  .meuspets-boto17,
  .meuspets-boto18,
  .meuspets-boto19,
  .meuspets-boto20,
  .meuspets-boto21 {
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .meuspets-boto10 {
    top: 444px;
    left: 601px;
    width: 239px;
    height: 42px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 40px;
    background-color: rgba(84, 86, 47, 1);
  }
  
  .meuspets-text13 {
    top: 11px;
    left: 50px;
    color: rgba(255, 249, 238, 1);
    height: auto;
    position: absolute;
    font-size: 16px;
    font-weight: 700;
    text-align: center;
  }
  
  .meuspets-boto11,
  .meuspets-boto12 {
    border-color: rgba(84, 86, 47, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 50px;
    background-color: rgba(84, 86, 47, 1);
  }
  
  .meuspets-text14,
  .meuspets-text15,
  .meuspets-text24,
  .meuspets-text25,
  .meuspets-text27,
  .meuspets-text28,
  .meuspets-text29,
  .meuspets-text30,
  .meuspets-text31,
  .meuspets-text32,
  .meuspets-text33,
  .meuspets-text34,
  .meuspets-text35 {
    color: rgba(84, 86, 47, 1);
    height: auto;
    position: absolute;
    font-size: 14px;
    font-weight: 700;
    text-align: center;
  }
  
  .meuspets-text14,
  .meuspets-text15,
  .meuspets-text25,
  .meuspets-text28,
  .meuspets-text31,
  .meuspets-text33,
  .meuspets-text34 {
    font-size: 14px;
  }
  
  .meuspets-text16,
  .meuspets-text17,
  .meuspets-text18,
  .meuspets-text19,
  .meuspets-text20,
  .meuspets-text21 {
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
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
    width: 1440px;
    height: 136px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .meuspets-line1 {
    top: 136px;
    left: 0px;
    width: 1440px;
    height: 1px;
    position: absolute;
  }
  
  .meuspets-text16 {
    top: 53px;
    left: 865px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
  }
  
  .meuspets-text17 {
    top: 53px;
    left: 1155px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
  }
  
  .meuspets-text18 {
    top: 53px;
    left: 709px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
  }
  
  .meuspets-text19 {
    top: 53px;
    left: 1025px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
  }
  
  .meuspets-text20 {
    top: 53px;
    left: 579px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
  }
  
  .meuspets-text21 {
    top: 53px;
    left: 435px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
  }
  
  .meuspets-logo {
    top: 34px;
    left: 95px;
    width: 48px;
    height: 68px;
    display: flex;
    position: absolute;
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
    top: 0px;
    left: 0px;
    width: 24px;
    height: 22px;
    border-radius: 100px 0 0 100px;
    background-color: rgba(71, 92, 54, 1);
  }
  
  .meuspets-frame24 {
    top: 0px;
    left: 24px;
    width: 24px;
    height: 22px;
    border-radius: 0 100px 100px 0;
    background-color: rgba(146, 151, 129, 1);
  }
  
  .meuspets-frame21 {
    top: 22px;
    left: 0px;
    width: 24px;
    height: 24px;
    border-radius: 100px 0 0 100px;
    background-color: rgba(151, 166, 126, 1);
  }
  
  .meuspets-frame23 {
    top: 22px;
    left: 24px;
    width: 24px;
    height: 24px;
    border-radius: 100px;
    background-color: rgba(74, 94, 56, 1);
  }
  
  .meuspets-frame22 {
    top: 46px;
    left: 0px;
    width: 24px;
    height: 22px;
    border-radius: 100px 0 100px 100px;
    background-color: rgba(55, 70, 40, 1);
  }
  
  .meuspets-botopadrofontemaior {
    top: 41px;
    left: 1256px;
    width: 89px;
    height: 53px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .meuspets-boto13 {
    top: 0px;
    left: 0px;
    width: 89px;
    height: 53px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 40px;
    background-color: rgba(84, 86, 47, 1);
  }
  
  .meuspets-text22 {
    top: 13px;
    left: 21px;
    color: rgba(255, 249, 238, 1);
    height: auto;
    position: absolute;
    font-size: 22px;
    font-weight: 700;
    text-align: center;
  }
  
  .meuspets-card {
    top: 810px;
    left: 119px; /* Será ajustado dinamicamente */
    width: 170px;
    height: 268px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .meuspets-text23,
  .meuspets-text26,
  .meuspets-text29,
  .meuspets-text32 {
    top: 179px;
    color: rgba(84, 86, 47, 1);
    height: auto;
    position: absolute;
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
    border-color: rgba(55, 57, 25, 1);
    border-radius: 200px;
  }
  
  .meuspets-botopadro,
  .meuspets-botopadro1,
  .meuspets-botopadro2,
  .meuspets-botopadro3,
  .meuspets-botopadro4 {
    width: 129px;
    height: 22px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
  
  .meuspets-boto {
    top: 0px;
    left: 0px;
    width: 129px;
    height: 22px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 40px;
    background-color: rgba(84, 86, 47, 1);
  }
  
  .meuspets-text {
    top: 1px;
    left: 37px;
    color: rgba(255, 249, 238, 1);
    height: auto;
    position: absolute;
    font-size: 14px;
    font-weight: 700;
    text-align: center;
  }
  
  .meuspets-boto-editar {
    top: 0px;
    left: 0px;
    width: 129px;
    height: 22px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(84, 86, 47, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 40px;
    background-color: rgba(255, 255, 255, 1);
  }
  
  .meuspets-text-editar {
    top: 1px;
    left: 41px;
    color: rgba(84, 86, 47, 1);
    height: auto;
    position: absolute;
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
    width: 1440px;
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
    top: 35px;
    left: 454px;
    color: rgba(163, 164, 149, 1);
    height: auto;
    position: absolute;
    font-size: 24px;
    font-weight: 500;
    text-align: left;
  }
  
  /* Hover Effects */
  .meuspets-boto10:hover,
  .meuspets-boto13:hover,
  .meuspets-boto14:hover,
  .meuspets-boto16:hover,
  .meuspets-boto18:hover,
  .meuspets-boto20:hover {
    background-color: #373919;
  }
  
  .meuspets-boto11:hover,
  .meuspets-boto12:hover,
  .meuspets-boto15:hover,
  .meuspets-boto17:hover,
  .meuspets-boto19:hover,
  .meuspets-boto21:hover {
    background-color: rgba(84, 86, 47, 1);
  }
  
  .meuspets-boto11:hover .meuspets-text14,
  .meuspets-boto12:hover .meuspets-text15,
  .meuspets-boto15:hover .meuspets-text25,
  .meuspets-boto17:hover .meuspets-text28,
  .meuspets-boto19:hover .meuspets-text31,
  .meuspets-boto21:hover .meuspets-text35 {
    color: rgba(255, 249, 238, 1);
  }
  
  .meuspets-text16:hover,
  .meuspets-text17:hover,
  .meuspets-text18:hover,
  .meuspets-text19:hover,
  .meuspets-text20:hover,
  .meuspets-text21:hover {
    text-decoration: underline;
  }
  
  /* Media Queries para Responsividade */
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
      top: 20px; /* Ajuste conforme necessário */
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
    .meuspets-boto13,
    .meuspets-boto14,
    .meuspets-boto15,
    .meuspets-boto16,
    .meuspets-boto17,
    .meuspets-boto18,
    .meuspets-boto19,
    .meuspets-boto20,
    .meuspets-boto21 {
      left: 50%;
      transform: translateX(-50%);
    }
  
    .meuspets-navbarcliente {
      width: 100%;
      height: auto;
      flex-direction: column;
      align-items: center;
    }
  
    .meuspets-text16,
    .meuspets-text17,
    .meuspets-text18,
    .meuspets-text19,
    .meuspets-text20,
    .meuspets-text21 {
      left: 50%;
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
      margin-top: 10px;
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
  