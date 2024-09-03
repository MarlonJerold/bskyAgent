# BskyAgent

BskyAgent é uma biblioteca Java para interagir com o Bsky API, permitindo autenticação, criação de posts e recuperação de perfis de usuário. Esta biblioteca foi projetada para ser usada em aplicações Java que precisam se integrar com o Bsky de forma simples e eficaz.

## Funcionalidades

- Autenticação com a API do Bsky usando identificadores e senhas de aplicativo.
- Criação de posts no feed do Bsky.
- Recuperação de perfis de usuário do Bsky.

## Requisitos

- Java 8 ou superior.
- [OkHttp](https://square.github.io/okhttp/) para realizar requisições HTTP.
- [JSON-java](https://github.com/stleary/JSON-java) para manipulação de JSON.
- [Jackson](https://github.com/FasterXML/jackson) para deserialização de JSON em objetos Java.

## Instalação

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/bsky-agent.git
    ```

2. Adicione as dependências ao seu projeto Java. Se estiver usando Maven, adicione os seguintes elementos ao seu `pom.xml`:

    ```xml
    <dependencies>
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>4.9.1</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.0</version>
        </dependency>
    </dependencies>
    ```

## Uso

### Autenticação

```java
String handle = "seu-handle";
String appPassword = "sua-senha-de-app";
BskyAgent agent = new BskyAgent(handle, appPassword);
```

Criar um Post
```java
String texto = "Olá, Bsky!";
agent.createPost(texto);
```

Consultar a Thread de um Post

```java
blueskyClient.getPostThread("url do Post");
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

Contato
Para dúvidas, sugestões ou feedback, você pode entrar em contato através de jeroldmarlon5@gmail.com.
