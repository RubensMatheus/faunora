<!-- src/components/ModalCadastrarPet.vue -->
<template>
  <div v-if="isVisible" class="modal-overlay">
    <div class="home-container">
      <div class="home-cadastrarpet">
        <div class="home-formulrio">
          <!-- Campo: Peso -->
          <div class="home-assinatura1">
            <div class="home-assinatura2">
              <span class="home-text10">Peso</span>
            </div>
            <input 
              v-model="peso" 
              class="home-text11" 
              placeholder="Digite aqui..." 
              type="number" 
              min="0" 
              step="0.1"
            />
          </div>

          <!-- Campo: Espécie -->
          <div class="home-sintomas1">
            <div class="home-sintomas2">
              <span class="home-text12">Espécie</span>
            </div>
            <input 
              v-model="especie" 
              class="home-text13" 
              placeholder="Digite aqui..." 
              type="text" 
            />
          </div>

          <!-- Campo: Nome do Pet -->
          <div class="home-validade1">
            <div class="home-texto1">
              <span class="home-text14">Nome</span>
            </div>
            <input 
              v-model="nomePet" 
              class="home-text15" 
              placeholder="Digite aqui..." 
              type="text" 
            />
          </div>

          <!-- Campo: Nome do Tutor (Pré-preenchido) -->
          <div class="home-validade2">
            <div class="home-texto2">
              <span class="home-text16">Nome do(a) tutor(a)</span>
            </div>
            <span class="home-text17">{{ nomeTutor }}</span>
          </div>

          <!-- Botão "Voltar" -->
          <span class="home-text18" @click="closeModal">voltar</span>

          <!-- Campo: Data de Nascimento -->
          <div class="home-validade3">
            <div class="home-validadedo-laudo1">
              <span class="home-text19">Data de nascimento</span>
            </div>
            <input 
              v-model="dataNascimento" 
              class="home-text20" 
              placeholder="DD/MM/AAAA" 
              type="date" 
            />
            <img
              src="../assets/search12323-rndnw.svg"
              alt="calendarweeksvgrepocom12991"
              class="home-calendarweeksvgrepocom1"
            />
          </div>

          <!-- Campo: Sexo -->
          <div class="home-sexo">
            <div class="home-validadedo-laudo2">
              <span class="home-text21">Sexo</span>
            </div>
            <div class="radio-group">
              <label class="radio-item">
                <input 
                  type="radio" 
                  name="sexo" 
                  value="Fêmea" 
                  v-model="sexo" 
                />
                <span class="custom-radio"></span>
                Fêmea
              </label>
              <label class="radio-item">
                <input 
                  type="radio" 
                  name="sexo" 
                  value="Macho" 
                  v-model="sexo" 
                />
                <span class="custom-radio"></span>
                Macho
              </label>
            </div>
          </div>

          <!-- Botão "Cadastrar" -->
          <div class="home-botopadro">
            <button class="home-boto" @click="cadastrarPet">
              <span class="home-text24">CADASTRAR</span>
            </button>
          </div>
        </div>

        <div class="home-frame14">
          <img
            src="../assets/search12323-rndnw.svg"
            alt="editsvgrepocom12991"
            class="home-editsvgrepocom1"
          />
        </div>
      </div>
    </div>
  </div>
</template>

  
<script lang="ts">
import { defineComponent, ref } from 'vue';
import apiClient from '../services/api'; // Certifique-se de que o caminho está correto

export default defineComponent({
  name: 'ModalCadastrarPet',
  props: {
    isVisible: {
      type: Boolean,
      required: true,
    },
    nomeTutor: {
      type: String,
      required: true,
    },
  },
  setup(props, { emit }) {
    // Propriedades reativas para os campos do formulário
    const peso = ref<number | null>(null);
    const especie = ref<string>('');
    const nomePet = ref<string>('');
    const dataNascimento = ref<string>('');
    const sexo = ref<string>('');

    // Método para fechar o modal
    const closeModal = () => {
      emit('close');
    };

    // Método para cadastrar o pet
    const cadastrarPet = async () => {
      // Validações básicas
      if (!peso.value || !especie.value || !nomePet.value || !dataNascimento.value || !sexo.value) {
        alert('Por favor, preencha todos os campos.');
        return;
      }

      try {
        const response = await apiClient.post('/pets', {
          nome: nomePet.value,
          tipo: especie.value,
          sexo: sexo.value,
          peso: peso.value,
          dataNascimento: dataNascimento.value
        });

        console.log(response.data); // Dados retornados pelo backend
        alert('Pet cadastrado com sucesso!');
        emit('add-pet', response.data); // Emite o evento para adicionar o pet na lista do componente pai
        closeModal(); // Fecha o modal após o cadastro
      } catch (error: any) {
        console.error(error);
        if (error.response && error.response.data) {
          const mensagens = Object.values(error.response.data);
          const mensagemFinal = mensagens.join('\n');
          alert(`Erro: \n${mensagemFinal}`);
        } else {
          alert('Erro ao cadastrar pet. Tente novamente mais tarde.');
        }
        closeModal();
      }
    };

    return {
      peso,
      especie,
      nomePet,
      dataNascimento,
      sexo,
      closeModal,
      cadastrarPet,
    };
  },
});
</script>


  
  <style scoped>
  .modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
    }
    
  .home-container {
    width: 100%;
    display: flex;
    min-height: 100vh;
    align-items: center;
    flex-direction: column;
    justify-content: center;
  }
   
  .home-cadastrarpet {
    width: 697px;
    height: 546px;
    display: flex;
    overflow: hidden;
    position: relative;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 30px;
    background-color: rgba(255, 255, 255, 1);
  }
   
  .home-formulrio {
    top: 55px;
    left: 41px;
    width: 615px;
    height: 437px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
   
  .home-assinatura1 {
    top: 74px;
    left: 216px;
    width: 399px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
   
  .home-assinatura2 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 54px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
   
  .home-text10 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-text11 {
    top: 15px;
    left: 28px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    width: 340px;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-sintomas1 {
    top: 300px;
    left: 0px;
    width: 615px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
   
  .home-sintomas2 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 76px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
   
  .home-text12 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-text13 {
    top: 15px;
    left: 28px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    width: 560px;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-validade1 {
    top: 225px;
    left: 0px;
    width: 301px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
   
  .home-texto1 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 62px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
   
  .home-text14 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-text15 {
    top: 15px;
    left: 28px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    width: 240px;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-validade2 {
    top: 225px;
    left: 314px;
    width: 301px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
   
  .home-texto2 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 169px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
   
  .home-text16 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-text17 {
    top: 15px;
    left: 28px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-text18 {
    top: 423px;
    left: 287px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 15px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-validade3 {
    top: 0px;
    left: 216px;
    width: 399px;
    height: 51px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-color: rgba(123, 123, 123, 1);
    border-style: solid;
    border-width: 1px;
    border-radius: 8px;
  }
   
  .home-validadedo-laudo1 {
    gap: 8px;
    top: -9px;
    left: 20px;
    width: 169px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
   
  .home-text19 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-text20 {
    top: 15px;
    left: 28px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    width: 300px;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-calendarweeksvgrepocom1 {
    top: 9px;
    left: 353px;
    width: 32px;
    height: 32px;
    position: absolute;
  }
   
  .home-sexo {
    top: 139px;
    left: 236px;
    width: 203px;
    height: 47px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
   
  .home-validadedo-laudo2 {
    gap: 8px;
    top: 0px;
    left: 0px;
    width: 54px;
    display: flex;
    padding: 0 8px;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 1);
  }
   
  .home-text21 {
    color: rgba(123, 123, 123, 1);
    height: auto;
    font-size: 16px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-frame81 {
    top: 25px;
    left: 8px;
    width: 83px;
    height: 22px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
   
  .home-text22 {
    top: 25px;
    left: 37px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    opacity: 0.90;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-ellipse21 {
    top: 25px;
    left: 8px;
    width: 22px;
    height: 22px;
    position: absolute;
  }
   
  .home-frame131 {
    top: 28px;
    left: 11px;
    width: 16px;
    height: 16px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
   
  .home-ellipse31 {
    top: 0px;
    left: 0px;
    width: 16px;
    height: 16px;
    position: absolute;
  }
   
  .home-frame82 {
    top: 25px;
    left: 120px;
    width: 83px;
    height: 22px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
   
  .home-text23 {
    left: 29px;
    color: rgba(67, 67, 67, 1);
    height: auto;
    opacity: 0.90;
    position: absolute;
    font-size: 18px;
    font-style: Medium;
    text-align: left;
    font-family: Inter;
    font-weight: 500;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-ellipse22 {
    top: 0px;
    left: 0px;
    width: 22px;
    height: 22px;
    position: absolute;
  }
   
  .home-frame132 {
    top: 3px;
    left: 3px;
    width: 16px;
    height: 16px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
   
  .home-ellipse32 {
    top: 0px;
    left: 0px;
    width: 16px;
    height: 16px;
    position: absolute;
  }
   
  .home-botopadro {
    top: 377px;
    left: 208px;
    width: 201px;
    height: 40px;
    display: flex;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
  }
   
  .home-boto {
    top: 0px;
    left: 0px;
    width: 201px;
    height: 40px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 40px;
    background-color: rgba(84, 86, 47, 1);
  }
   
  .home-text24 {
    top: 10px;
    left: 53px;
    color: rgba(255, 249, 238, 1);
    height: auto;
    position: absolute;
    font-size: 16px;
    font-style: Semi Bold;
    text-align: center;
    font-family: Inter;
    font-weight: 700;
    line-height: normal;
    font-stretch: normal;
    text-decoration: none;
  }
   
  .home-frame14 {
    top: 59px;
    left: 52px;
    width: 170px;
    height: 170px;
    display: flex;
    overflow: hidden;
    position: absolute;
    align-items: flex-start;
    flex-shrink: 0;
    border-radius: 100px;
    background-color: rgba(84, 86, 47, 0.46000000834465027);
  }
   
  .home-editsvgrepocom1 {
    top: 50px;
    left: 50px;
    width: 70px;
    height: 70px;
    transform: scale(1);
    transition: transform 0.3s ease; 
    position: absolute;
  }
  
  .home-editsvgrepocom1:hover {
    transform: scale(1.3);
  }
  
  
  .home-boto:hover {
    background-color: #373919;
  }
  
  .home-text18:hover {
    text-decoration: underline;
  }
  
  .radio-group {
    display: flex;
    margin-top: 29px;
    align-items: center;
    font-weight: 500;
    gap: 20px;
  }
  
  .radio-group {
    margin-left: 9px;
  }
  
  .radio-item {
    display: flex;
    align-items: center;
    cursor: pointer;
  }
  
  .radio-item input[type="radio"] {
    display: none;
  }
  
  .custom-radio {
    width: 22px;
    height: 22px;
    background-color: #d9d9d9;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: 10px;
    font-family: Inter;
    transition: background-color 0.3s;
  }
  
  .custom-radio::after {
    content: '';
    width: 13px;
    height: 13px;
    background-color: transparent;
    border-radius: 50%;
    transform: scale(1);
    transition: background-color 0.3s, transform 0.3s;
  }
  
  input[type="radio"]:checked + .custom-radio::after {
    background-color: #54562f;
    transform: scale(1.2);
  }
  
  label {
    font-size: 18px;
    color: #444444;
  }
  </style>