## TDE-Mobile-PI-DEV-OPS

Este projeto é uma aplicação mobile desenvolvida como parte da disciplina de Projeto Integrador (PI) no curso de Sistemas de Informação do Centro Universitário Paraíso (UNIFAP).  
Seu objetivo é aplicar práticas de DevOps no desenvolvimento de aplicativos móveis utilizando Kotlin.

## 📱 Sobre o Projeto

O TDE-Mobile-PI-DEV-OPS visa integrar conceitos de Engenharia de Software e DevOps no desenvolvimento de uma aplicação mobile.  
O projeto é estruturado para facilitar a colaboração entre equipes e a automação de processos de desenvolvimento e implantação.

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: Linguagem principal para o desenvolvimento da aplicação.
- **Gradle**: Sistema de automação de build utilizado para gerenciar dependências e compilar o projeto.
- **GitHub Actions**: Ferramenta de integração contínua e entrega contínua (CI/CD) para automatizar fluxos de trabalho.

## 📁 Estrutura do Projeto

O repositório está organizado da seguinte forma:

```plaintext
├── app/                   # Código-fonte do aplicativo
├── Dev ops/               # Scripts e configurações relacionados a DevOps
├── Eng software/          # Documentação e artefatos de engenharia de software
├── gradle/                # Configurações do Gradle
├── .gitignore             # Arquivos e pastas ignorados pelo Git
├── build.gradle.kts       # Script de build do Gradle em Kotlin
├── gradle.properties      # Propriedades do Gradle
├── gradlew                # Script para executar o Gradle no Unix
├── gradlew.bat            # Script para executar o Gradle no Windows
└── settings.gradle.kts    # Configurações do projeto Gradle


👥 Desenvolvedores

O projeto conta com a colaboração dos seguintes desenvolvedores:

Aristóteles Alves de Oliveira
Lucas Pierre Araújo
Evellyn Santos
Caio Graco
Isaac Wanderson de Pontes Xavier
Natanael Felix
Matheus Wenes
Pedro Henrique

## 🚀 Descrição da API Utilizada


Este app utiliza Retrofit para consumir uma API REST externa para obter e manipular informações como (usuários, produtos, tarefas, etc.). A comunicação ocorre via HTTP, com troca de dados em JSON e utilização dos métodos padrão:


- `GET` – para buscar dados
- `POST` – para envio de novos dados
- `PUT` – para atualizar informações existentes
- `DELETE` – para remoção de dados


## 📡 Endpoints Implementados no App


- `GET /usuarios` – lista todos os usuários
- `POST /usuarios` – cria um novo usuário
- `GET /usuarios/{id}` – detalhes de um usuário
- `PUT /usuarios/{id}` – atualiza um usuário existente
- `DELETE /usuarios/{id}` – remove um usuário

## 📦 Bibliotecas Utilizadas para Requisições


As principais bibliotecas utilizadas no consumo da API REST foram:


- [Retrofit](https://square.github.io/retrofit/) – cliente HTTP para Android e Java, utilizado para facilitar a comunicação com APIs REST.
- [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) – para serialização e desserialização de objetos JSON.
- [OkHttp](https://square.github.io/okhttp/) – usado em conjunto com Retrofit para gerenciamento das requisições HTTP.


## 🛠️ Instruções de Build e Execução


### Pré-requisitos


- Android Studio (recomendado)
- JDK 11 ou superior
- Gradle (já incluso via wrapper)
- Emulador ou dispositivo Android


### Passos para execução


1. Clone o repositório:
   ```bash
   git clone https://github.com/AristotelesAlves/TDE-Mobile-PI-DEV-OPS.git
   cd TDE-Mobile-PI-DEV-OPS


2. Abra o projeto no Android Studio:


    Vá em File > Open e selecione a pasta do projeto


    Aguarde o Gradle sincronizar


3. Rode o projeto:


    Conecte um dispositivo Android ou inicie um emulador


    Clique no botão Run (▶️) ou use Shift + F10


### Compilar manualmente via terminal


./gradlew clean assembleDebug
./gradlew installDebug

