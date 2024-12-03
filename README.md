# Nupsign 0.1.0

Nupsign é um projeto que visa facilitar e reduzir o tempo dos cartórios na hora de realizar um novo casamento. Através de um sistema de cadastro de casais, o cartório pode realizar o casamento de forma mais rápida e eficiente.

## Súmario 

- [Sobre o projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [Como executar o projeto](#como-executar-o-projeto)
- [Endpoints](#endpoints)
- [Autores](#autores)
- [Licença](#licença)

## Sobre o projeto

O projeto foi desenvolvido para a disciplina de Inovação, sustentabilidade e competitividade empresarial, ministrada pela professora Sônia Santana da Universidade Una de Uberlândia.

## Funcionalidades

- Cadastro de usuários
- Upload de documentos
- Assinatura digital

## Tecnologias utilizadas

- Spring Boot
- Spring Security
- Spring Data JPA

## Como executar o projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

- [Git](https://git-scm.com)
- [Java JDK 23](https://www.oracle.com/java/technologies/downloads/#java21)

Além disto é bom ter um editor para trabalhar com o código como [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/)
ou [Eclipse](https://www.eclipse.org/downloads/)

### Rodando o Back End (servidor)
#### Clone este repositório
    
```
$ git clone
```

#### Acesse a pasta do projeto no terminal/cmd

```
$ cd nupsign
```

#### Importe o projeto no IntelliJ IDEA ou Eclipse

Em seguida, abra o projeto em seu editor de código. Após isso, configure o arquivo `application.properties` com as informações do seu banco de dados.

Com o banco de dados configurado, execute o projeto.

##
```bash
$ ./mvnw spring-boot:run
```

O servidor inciará na porta:8080 - acesse [http://localhost:8080](http://localhost:8080)

## Endpoints

### Usuários

#### POST /auth/register

Cria um novo usuário e retorna o objeto criado.

```json
{
    "nome": "{nome}",
    "email": "{email}",
    "senha": "{senha}"
}
```

#### POST /auth/login

Realiza o login do usuário e retorna um token de autenticação JWT válido por 2 horas, com o qual o usuário pode acessar os endpoints protegidos e retorna o objeto criado.

```json
{
    "email": "{email}",
    "senha": "{senha}"
}
```
Para configurar o token no Postman, vá em Authorization -> Type -> Bearer Token e cole o token no campo Token.

Para mudar o tempo de expiração do token, vá em application.properties e altere a propriedade jwt.expiration.


## Autores

- [Enzo Nascimento Cabrera](https://github.com/EnzoCabrera)
- [Geziel Oliveira Silva](https://github.com/ImG1029)
- [Victor Martins de Sousa](https://github.com/VictorMS-200)

## Licença

Este projeto está licenciado sob a licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter mais detalhes.
