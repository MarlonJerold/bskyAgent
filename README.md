# BskyAgent

BskyAgent é uma biblioteca Java para interagir com o Bsky API, permitindo autenticação, criação de posts e recuperação de perfis de usuário. Esta biblioteca foi projetada para ser usada em aplicações Java que precisam se integrar com o Bsky de forma simples e eficaz.

# Sumário

- [BskyAgent](#bskyagent)
  - [Requisitos](#requisitos)
  - [Instalação](#instalação)
    - [Clonando o Repositório](#1-clone-o-repositório)
  - [Uso](#uso)
    - [Autenticação](#autenticação)
    - [Post](#post)
    - [Profile](#profile)
    - [Consultar a Thread de um Post](#consultar-a-thread-de-um-post)
  - [Exceções e Tratamento de Erros](#exceções-e-tratamento-de-erros)
  - [Contribuindo](#contribuindo)
  - [Licença](#licença)
  - [Contato](#contato)

## Requisitos

- Java 8 ou superior.
- [OkHttp](https://square.github.io/okhttp/) para realizar requisições HTTP.
- [JSON-java](https://github.com/stleary/JSON-java) para manipulação de JSON.
- [Jackson](https://github.com/FasterXML/jackson) para deserialização de JSON em objetos Java.

## Instalação

### 1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/bsky-agent.git
    ```

## Uso

### Autenticação

```java
String handle = "seu-handle";
String appPassword = "sua-senha-de-app";
BskyAgent agent = new BskyAgent(handle, appPassword);
```

#### Post
```java
String texto = "Olá, Bsky!";
agent.createPost(texto);
```

#### Profile
```java
String actor = "patinho.tech"
agent.getProfile(String actor)
```
Você irá precisar importar a classe Profile

```java
String actor = "patinho.tech"
Profile profile = agent.getProfile("patinho.tech");
```

### Consultar a Thread de um Post

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
