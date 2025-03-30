# 🐾 Faunora - Sistema para Clínica Veterinária  

**Faunora** é um aplicativo desenvolvido para auxiliar na gestão de clínicas veterinárias, oferecendo funcionalidades para cadastro de clientes, agendamento de consultas, registro de pacientes e gerenciamento de serviços.  

## 🚀 Tecnologias Utilizadas  

### **Frontend:**  
- Vue.js 3 + Vite  
- Vue Router  
- Pinia (gerenciamento de estado)  
- Axios (requisições HTTP)  
- TailwindCSS (estilização)  

### **Backend:**  
- Spring Boot  
- JPA + Hibernate  
- PostgreSQL 

---

## 📦 Instalação e Execução  

### 🔧 **Pré-requisitos:**  
Certifique-se de ter instalado:  
- [Node.js](https://nodejs.org/)  
- [Vue CLI](https://cli.vuejs.org/)  
- [Java 17+](https://adoptium.net/)  

### 🖥 **Rodando o Frontend**  

```bash
# Clone o repositório
git clone -b frontend-vue https://github.com/RubensMatheus/faunora.git

# Acesse a pasta do projeto
cd faunora/frontend

# Instale as dependências
npm install

# Inicie a aplicação
npm run dev
```

O frontend estará disponível em **http://localhost:5173**.  

---

### ⚙ **Rodando o Backend**  

```bash
# Acesse a pasta do backend
cd faunora/backend

# Compile o projeto
./mvnw clean package

# Execute o backend
java -jar target/*.jar
```

O backend estará disponível em **http://localhost:8080**.  

---

## 📜 Funcionalidades  

✔ Cadastro e gerenciamento de clientes e veterinários  
✔ Registro de animais e histórico veterinário  
✔ Agendamento e gerenciamento de consultas  
✔ Serviço de reserva de produtos de pet shop

---

## 📄 Licença  

Este projeto está sob a licença **MIT**.  

