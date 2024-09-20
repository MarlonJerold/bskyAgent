# BskyAgent

BskyAgent é uma biblioteca que é e está sendo projetada para interagir com o Bsky API de forma simples e eficaz para aplicações que utilizam Java.

# Sumário

- [BskyAgent](#bskyagent)
  - [Requisitos](#requisitos)
  - [Instalação](#instalação)
  - [Uso](#uso)
    - [Autenticação](#autenticação)
    - [CreatePost](#createpost)
    - [GetProfile](#getprofile)
    - [GetPostThread](#getpostthread)
  - [Exceções e Tratamento de Erros](#exceções-e-tratamento-de-erros)
  - [Contribuindo](#contribuindo)
  - [Licença](#licença)
  - [Contato](#contato)

## Requisitos

- Java 8 ou superior.
- Maven
- Arquivo pom.xml

## Instalação

### 1. Clone o repositório:

```bash
    git clone https://github.com/MarlonJerold/bskyAgent.git
```

Navegue até o projeto 
```java
cd bskyAgent
```
Rode o seguinte comando, certifique que você tenha o Maven instalado em seu ambiente:

```java
mvn clean install
```

Adicionar dependência em arquivo `pom.xml`:


    <dependency>
        <groupId>org.bluesky</groupId>
        <artifactId>bluesky-library</artifactId>
        <version>1.0.0</version>
    </dependency>

## Uso

### Autenticação

```java
String handle = "seu-handle";
String appPassword = "sua-senha-de-app";
BskyAgent agent = new BskyAgent(handle, appPassword);
```

#### CreatePost
```java
String texto = "Olá, Bsky!";
agent.createPost(texto);
```

#### GetProfile
```java
String actor = "patinho.tech"
agent.getProfile(String actor)
```
Você irá precisar importar a classe Profile

```java
String actor = "patinho.tech"
Profile profile = agent.getProfile("patinho.tech");
```

#### GetPostThread

```java
agent.getPostThread("url do Post");
```

## Exceções e Tratamento de Erros
BskyAgent lança IOException em caso de falha nas requisições HTTP ou problemas na comunicação com a API. Certifique-se de tratar essas exceções adequadamente no seu código para lidar com falhas de rede ou respostas inesperadas da API.

## Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests. Antes de contribuir, por favor leia o guia de contribuição.

Faça um fork do projeto
Crie uma nova branch: 
```bash
git checkout -b my-feature
```
Faça suas mudanças e commit: 
```bash
git commit -m 'Add my feature'
```
Envie para a branch original: 
```bash
git push origin my-feature
```
Abra um pull request
## Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.

### Contato
Para dúvidas, sugestões ou feedback, você pode entrar em contato através de jeroldmarlon5@gmail.com.
