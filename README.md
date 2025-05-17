### PetCare Digital Solutions

Sistema de gerenciamento para clínicas veterinárias que permite o controle de tutores, animais, veterinários, consultas, vacinações e prontuários.

## Participantes do Projeto

| Nome | RA
|-----|-----
|  | 
|  | 
|  | 
|  | 
|  | 


## Diagrama de Fluxo

![image](https://github.com/user-attachments/assets/b8a50330-3069-48e8-9678-78b9421dcfea)


## Sobre o Projeto

O PetCare Digital Solutions é um sistema completo para gerenciamento de clínicas veterinárias, desenvolvido com Spring Boot. O sistema permite:

- Cadastro e gerenciamento de tutores de animais
- Cadastro e gerenciamento de animais
- Cadastro e gerenciamento de veterinários
- Agendamento e controle de consultas
- Registro e controle de vacinações
- Manutenção de prontuários médicos


## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- RESTful API


## Instalação e Configuração

### Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.8 ou superior
- IDE de sua preferência (recomendado: IntelliJ IDEA ou VS Code)


### Passos para Instalação

1. Clone o repositório:

```shellscript
git clone https://github.com/ogiovannibr/petcare.git
cd petcare
```


2. Compile o projeto com Maven:

```shellscript
mvn clean install
```


3. Execute a aplicação:

```shellscript
mvn spring-boot:run
```


4. A aplicação estará disponível em `http://localhost:8081`


## Guia de Uso com Thunder Client

O Thunder Client é uma extensão do VS Code para testar APIs REST. Abaixo estão exemplos de como testar cada endpoint da API.

### Instalação do Thunder Client

1. Abra o VS Code
2. Vá para a aba de extensões (Ctrl+Shift+X)
3. Pesquise por "Thunder Client"
4. Clique em "Install"


### Configurando uma Coleção

1. Abra o Thunder Client na barra lateral do VS Code
2. Clique em "Collections"
3. Clique no botão "+" para criar uma nova coleção
4. Nomeie como "PetCare API"


### Testando os Endpoints

#### 1. Gerenciamento de Tutores

**Listar todos os tutores**

- Método: GET
- URL: [http://localhost:8081/api/tutores](http://localhost:8081/api/tutores)


**Buscar tutor por ID**

- Método: GET
- URL: [http://localhost:8081/api/tutores/1](http://localhost:8081/api/tutores/1)


**Criar novo tutor**

- Método: POST
- URL: [http://localhost:8081/api/tutores](http://localhost:8081/api/tutores)
- Headers: Content-Type: application/json
- Body:

```json
{
  "nome": "João Silva",
  "cpf": "12345678900",
  "telefone": "(11) 98765-4321",
  "email": "joao.silva@email.com",
  "endereco": "Rua das Flores, 123"
}
```




**Atualizar tutor**

- Método: PUT
- URL: [http://localhost:8081/api/tutores/1](http://localhost:8081/api/tutores/1)
- Headers: Content-Type: application/json
- Body:

```json
{
  "nome": "João Silva Atualizado",
  "cpf": "12345678900",
  "telefone": "(11) 98765-4321",
  "email": "joao.silva.novo@email.com",
  "endereco": "Rua das Flores, 456"
}
```




**Excluir tutor**

- Método: DELETE
- URL: [http://localhost:8081/api/tutores/1](http://localhost:8081/api/tutores/1)


#### 2. Gerenciamento de Animais

**Listar todos os animais**

- Método: GET
- URL: [http://localhost:8081/api/animais](http://localhost:8081/api/animais)


**Buscar animal por ID**

- Método: GET
- URL: [http://localhost:8081/api/animais/1](http://localhost:8081/api/animais/1)


**Criar novo animal**

- Método: POST
- URL: [http://localhost:8081/api/animais](http://localhost:8081/api/animais)
- Headers: Content-Type: application/json
- Body:

```json
{
  "nome": "Rex",
  "especie": "Cachorro",
  "raca": "Labrador",
  "dataNascimento": "2020-01-15",
  "tutorId": 1
}
```




**Buscar animais por tutor**

- Método: GET
- URL: [http://localhost:8081/api/animais/tutor/1](http://localhost:8081/api/animais/tutor/1)


**Buscar animais por espécie**

- Método: GET
- URL: [http://localhost:8081/api/animais/especie/Cachorro](http://localhost:8081/api/animais/especie/Cachorro)


#### 3. Gerenciamento de Veterinários

**Listar todos os veterinários**

- Método: GET
- URL: [http://localhost:8081/api/veterinarios](http://localhost:8081/api/veterinarios)


**Criar novo veterinário**

- Método: POST
- URL: [http://localhost:8081/api/veterinarios](http://localhost:8081/api/veterinarios)
- Headers: Content-Type: application/json
- Body:

```json
{
  "nome": "Dra. Ana Santos",
  "crmv": "12345-SP",
  "telefone": "(11) 97654-3210",
  "email": "ana.santos@clinica.com",
  "especializacoes": ["Cachorro", "Gato", "Aves"]
}
```




**Buscar veterinários por especialização**

- Método: GET
- URL: [http://localhost:8081/api/veterinarios/especializacao/Cachorro](http://localhost:8081/api/veterinarios/especializacao/Cachorro)


#### 4. Gerenciamento de Consultas

**Listar todas as consultas**

- Método: GET
- URL: [http://localhost:8081/api/consultas](http://localhost:8081/api/consultas)


**Criar nova consulta**

- Método: POST
- URL: [http://localhost:8081/api/consultas](http://localhost:8081/api/consultas)
- Headers: Content-Type: application/json
- Body:

```json
{
  "dataHora": "2023-11-20T14:30:00",
  "local": "Clínica Central",
  "observacoes": "Consulta de rotina",
  "status": "AGENDADA",
  "animalId": 1,
  "veterinarioId": 1
}
```




**Verificar disponibilidade de veterinário**

- Método: GET
- URL: [http://localhost:8081/api/consultas/verificar-disponibilidade?veterinarioId=1&dataHora=2023-11-20T15:30:00](http://localhost:8081/api/consultas/verificar-disponibilidade?veterinarioId=1&dataHora=2023-11-20T15:30:00)


**Buscar próximas consultas**

- Método: GET
- URL: [http://localhost:8081/api/consultas/proximas](http://localhost:8081/api/consultas/proximas)


#### 5. Gerenciamento de Vacinações

**Listar todas as vacinações**

- Método: GET
- URL: [http://localhost:8081/api/vacinacoes](http://localhost:8081/api/vacinacoes)


**Registrar nova vacinação**

- Método: POST
- URL: [http://localhost:8081/api/vacinacoes](http://localhost:8081/api/vacinacoes)
- Headers: Content-Type: application/json
- Body:

```json
{
  "vacina": "Antirrábica",
  "lote": "AR2023-456",
  "dataAplicacao": "2023-11-15",
  "dataProximaDose": "2024-11-15",
  "animalId": 1,
  "veterinarioId": 1
}
```




**Buscar vacinações por animal**

- Método: GET
- URL: [http://localhost:8081/api/vacinacoes/animal/1](http://localhost:8081/api/vacinacoes/animal/1)


**Buscar próximas vacinas a vencer**

- Método: GET
- URL: [http://localhost:8081/api/vacinacoes/proximas-vencer?inicio=2023-11-01&fim=2023-12-31](http://localhost:8081/api/vacinacoes/proximas-vencer?inicio=2023-11-01&fim=2023-12-31)


#### 6. Gerenciamento de Prontuários

**Listar todos os prontuários**

- Método: GET
- URL: [http://localhost:8081/api/prontuarios](http://localhost:8081/api/prontuarios)


**Criar novo prontuário**

- Método: POST
- URL: [http://localhost:8081/api/prontuarios](http://localhost:8081/api/prontuarios)
- Headers: Content-Type: application/json
- Body:

```json
{
  "historico": "Animal saudável, sem problemas identificados.",
  "animalId": 1
}
```




**Buscar prontuário por animal**

- Método: GET
- URL: [http://localhost:8081/api/prontuarios/animal/1](http://localhost:8081/api/prontuarios/animal/1)


## Fluxo de Teste Completo

Para testar o sistema completo, siga este fluxo:

1. Crie um tutor
2. Crie um animal associado ao tutor
3. Crie um veterinário
4. Agendar uma consulta para o animal com o veterinário
5. Registre uma vacinação para o animal
6. Crie um prontuário para o animal
7. Consulte todas as informações do animal (consultas, vacinações e prontuário)
