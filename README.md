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

## Endpoints

### Usuários

#### POST /usuarios

Cria um novo usuário

```json
{
    "nome": "{nome}",
    "email": "{email}",
    "senha": "{senha}"
}
```


## Autores

- [Enzo Nascimento Cabrera](https://github.com/EnzoCabrera)
- [Geziel Oliveira Silva](https://github.com/ImG1029)
- [Victor Martins de Sousa](https://github.com/VictorMS-200)

## Licença

Este projeto está licenciado sob a licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter mais detalhes.
