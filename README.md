# 📝 Catolog API

API para gerenciar produtos e categorias, com integração AWS SNS/SQS para envio de mensagens e armazenamento de eventos de catálogo.
Esta API é parte do fluxo do projeto que envolve a [LambdaConsumer](https://github.com/vitoriadeveloper/LambdaConsumerCatalog)

## 📁 Estrutura do Projeto
```swift
src/main/java/com/vitoriadeveloper/catolog_api/
├── config
|   ├── aws
|        ├── AwsSnsConfig.java
|   ├── MongoDBConfig.java
├── controllers
│   ├── CategoryController.java
│   └── ProductController.java
├── domain
|    ├── exceptions
|          ├── CategoryNotFoundException
|          ├── GlobalExceptionHandler
|          ├── ProductNotFoundException
|    ├── Category.java
|    ├── Product.java
|
├── dto
│   ├── AwsMessageCategoryDTO.java
│   └── AwsMessageProductDTO.java
│   ├── CategoryRequestDTO.java
│   └── CategoryResponseDTO.java
│   ├── ProductRequestDTO.java
│   └── ProductResponseDTO.java
├── mappers
│   ├── CategoryMapper.java
│   └── ProductMapper.java
├── repositories
│   └── CategoryRepository.java
│   └── ProductRepository.java
└── services
    └── AwsSnsService.java
    └── CategoryService.java
    └── ProductService.java
```

## ⚙ Funcionalidades Principais
### Categorias
* CRUD completo de categorias.
* Envio de mensagens ao SNS em cada operação:
* Criação
* Atualização
* Exclusão

### Produtos
* CRUD completo de produtos.
* Validação da categoria associada.
* Envio de mensagens ao SNS em cada operação.

### Integração SNS/SQS
`AwsSnsService` é responsável por publicar eventos:

  * `publishCategory()` → Mensagens de categoria

  * `publishProdct()` → Mensagens de produto

As mensagens enviadas são consumidas por uma Lambda responsável por atualizar o catálogo em S3.


## 🔗 Tecnologias e dependencias
[![Minhas Habilidades](https://skillicons.dev/icons?i=mongodb,java,maven,aws)](https://skillicons.dev)

* Java 21
* Spring Boot 3+
* Spring Data MongoDB
* AWS SDK v2 (SNS)
* Jackson Databind


## 📦 Como rodar localmente
1. Clonar o repositório:
```bash
git clone https://github.com/vitoriadeveloper/catolog_api.git
```
2. Configurar `MongoDB` local ou remoto em `MongoDBConfig.java`, é recomendado que se faça o download no site do [MongoDb](https://www.mongodb.com/try/download/community)
3. Configurar credenciais AWS `(AWS_ACCESS_KEY_ID e AWS_SECRET_ACCESS_KEY)` no ambiente, também podem ser obtidas através da criação de um usuário no IAM no console da aws.
4. Build e run:
```bash
./mvnw clean install
./mvnw spring-boot:run
```

## 📌 Endpoints
### Categorias

<table>
  <thead>
    <tr>
      <th>Método</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>GET	/categorias</td>
      <td>Listar todas categorias</td>
    </tr>
    <tr>
      <td>POST	/categorias</td>
      <td>Criar nova categoria</td>
    </tr>
    <tr>
      <td>PUT	/categorias/{id}</td>
      <td>Atualizar categoria</td>
    </tr>
    <tr>
      <td>DELETE	/categorias/{id}</td>
      <td>Deletar categoria</td>
    </tr>
  </tbody>
</table>

### Produtos
<table>
  <thead>
    <tr>
      <th>Método</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>GET	/produtos</td>
      <td>Listar todoss produtos</td>
    </tr>
    <tr>
      <td>POST	/produtos</td>
      <td>Criar novo produto</td>
    </tr>
    <tr>
      <td>PUT	/produtos/{id}</td>
      <td>Atualizar produto</td>
    </tr>
    <tr>
      <td>DELETE	/produtos/{id}</td>
      <td>Deletar produto</td>
    </tr>
  </tbody>
</table>

## 📢 Fluxo SNS/SQS

1. Criação/atualização/exclusão em CategoryService ou ProductService → publica mensagem no SNS.
2. Uma Lambda (consumidor SQS) processa a mensagem e atualiza o catálogo em S3.
