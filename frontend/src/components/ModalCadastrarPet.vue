<template>
  <div class="modal-overlay" @click.self="closeModal">
    <div class="home-container">
      <div class="home-cadastrarpet">
        <div class="home-formulrio">
          <!-- Campo: Peso -->
          <div class="home-assinatura1">
            <div class="home-assinatura2">
              <span class="home-text10">Peso</span>
            </div>
            <input 
              class="home-text11" 
              placeholder="Digite aqui..." 
              v-model="peso"
              type="number"
              min="0"
            />
          </div>

          <!-- Campo: Espécie -->
          <div class="home-sintomas1">
            <div class="home-sintomas2">
              <span class="home-text12">Espécie</span>
            </div>
            <input 
              class="home-text13" 
              placeholder="Digite aqui..." 
              v-model="especie"
            />
          </div>

          <!-- Campo: Nome -->
          <div class="home-validade1">
            <div class="home-texto1">
              <span class="home-text14">Nome</span>
            </div>
            <input 
              class="home-text15" 
              placeholder="Digite aqui..." 
              v-model="nome"
            />
          </div>

          <!-- Campo: Nome do Tutor -->
          <div class="home-validade2">
            <div class="home-texto2">
              <span class="home-text16">Nome do(a) tutor(a)</span>
            </div>
            <span class="home-text17">{{ nomeTutor }}</span>
          </div>

          <!-- Texto: Voltar -->
          <span class="home-text18" @click="closeModal">Voltar</span>

          <!-- Campo: Data de Nascimento -->
          <div class="home-validade3">
            <div class="home-validadedo-laudo1">
              <span class="home-text19">Data de nascimento</span>
            </div>
            <input 
              class="home-text20" 
              placeholder="DD/MM/AAAA" 
              v-model="dataNascimento"
              type="date"
            />
            <img
              src="../assets/search12323-rndnw.svg"
              alt="Calendário"
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

          <!-- Botão: Cadastrar -->
          <div class="home-botopadro">
            <div class="home-boto" @click="cadastrarPet">
              <span class="home-text24">CADASTRAR</span>
            </div>
          </div>
        </div>

        <!-- Imagem Decorativa -->
        <div class="home-frame14">
          <img
            src="../assets/search12323-rndnw.svg"
            alt="Edição"
            class="home-editsvgrepocom1"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

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
  emits: ['close', 'add-pet'],
  setup(props, { emit }) {
    const peso = ref('');
    const especie = ref('');
    const nome = ref('');
    const dataNascimento = ref('');
    const sexo = ref('');

    const closeModal = () => {
      emit('close');
    };

    const cadastrarPet = () => {
      // Validações básicas
      if (!peso.value || !especie.value || !nome.value || !dataNascimento.value || !sexo.value) {
        alert('Por favor, preencha todos os campos.');
        return;
      }

      // Função para calcular a idade com base na data de nascimento
      const calcularIdade = (dataNascimento: string): number => {
        const hoje = new Date();
        const nascimento = new Date(dataNascimento);
        let idade = hoje.getFullYear() - nascimento.getFullYear();
        const mes = hoje.getMonth() - nascimento.getMonth();
        if (mes < 0 || (mes === 0 && hoje.getDate() < nascimento.getDate())) {
          idade--;
        }
        return idade;
      };

      const idadeCalculada = calcularIdade(dataNascimento.value);

      // Cria o objeto do pet
      const novoPet = {
        nome: nome.value,
        peso: Number(peso.value),
        especie: especie.value,
        dataNascimento: dataNascimento.value,
        idade: idadeCalculada,
        sexo: sexo.value,
        nomeTutor: props.nomeTutor,
        imagem: '../assets/default-pet.png',
      };

      // Emite o evento para adicionar o pet
      emit('add-pet', novoPet);

      // Fecha o modal
      closeModal();
    };

    return {
      peso,
      especie,
      nome,
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

.home-assinatura1,
.home-sintomas1,
.home-validade1,
.home-validade2,
.home-validade3 {
  display: flex;
  position: absolute;
  align-items: flex-start;
  flex-shrink: 0;
  border-color: rgba(123, 123, 123, 1);
  border-style: solid;
  border-width: 1px;
  border-radius: 8px;
}

.home-assinatura1 {
  top: 74px;
  left: 216px;
  width: 399px;
  height: 51px;
}

.home-sintomas1 {
  top: 300px;
  left: 0px;
  width: 615px;
  height: 51px;
}

.home-validade1 {
  top: 225px;
  left: 0px;
  width: 301px;
  height: 51px;
}

.home-validade2 {
  top: 225px;
  left: 314px;
  width: 301px;
  height: 51px;
}

.home-validade3 {
  top: 0px;
  left: 216px;
  width: 399px;
  height: 51px;
}

.home-assinatura2,
.home-sintomas2,
.home-texto1,
.home-texto2,
.home-validadedo-laudo1,
.home-validadedo-laudo2 {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(255, 255, 255, 1);
  padding: 0 8px;
}

.home-assinatura2 {
  top: -9px;
  left: 20px;
  width: 54px;
}

.home-sintomas2 {
  top: -9px;
  left: 20px;
  width: 76px;
}

.home-texto1 {
  top: -9px;
  left: 20px;
  width: 62px;
}

.home-texto2 {
  top: -9px;
  left: 20px;
  width: 169px;
}

.home-validadedo-laudo1 {
  top: -9px;
  left: 20px;
  width: 169px;
}

.home-validadedo-laudo2 {
  top: 0px;
  left: 0px;
  width: 54px;
}

.home-text10,
.home-text12,
.home-text14,
.home-text16,
.home-text19,
.home-text21 {
  color: rgba(123, 123, 123, 1);
  font-size: 16px;
  font-weight: 500;
  font-family: Inter, sans-serif;
  text-align: left;
}

.home-text11,
.home-text13,
.home-text15,
.home-text20 {
  color: rgba(67, 67, 67, 1);
  font-size: 18px;
  font-weight: 500;
  font-family: Inter, sans-serif;
  text-align: left;
  position: absolute;
}

.home-text18 {
  top: 423px;
  left: 287px;
  color: rgba(67, 67, 67, 1);
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
}

.home-text18:hover {
  text-decoration: underline;
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
}

.radio-group {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-left: 9px;
  margin-top: 29px;
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
  width: 201px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 40px;
  background-color: rgba(84, 86, 47, 1);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.home-boto:hover {
  background-color: #373919;
}

.home-text24 {
  color: rgba(255, 249, 238, 1);
  font-size: 16px;
  font-weight: 700;
  text-align: center;
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
  background-color: rgba(84, 86, 47, 0.46);
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
</style>
