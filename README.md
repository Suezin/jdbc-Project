# Gerenciador de Academia POO

Sistema desktop em Java para gerenciamento de academias, permitindo o controle de alunos, planos, pagamentos e frequência.

![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

## Sobre o projeto

Este projeto foi desenvolvido para praticar conceitos de Programação Orientada a Objetos (POO), persistência de dados com JDBC e modelagem de banco de dados relacional. O sistema simula as operações do dia a dia de uma academia, desde o cadastro de alunos até o controle financeiro básico.

## Funcionalidades

- [x] Cadastro, edição e exclusão de alunos
- [x] Cadastro de planos (mensal, trimestral, anual)
- [x] Controle de pagamentos e vencimentos
- [ ] Geração de relatórios em PDF
- [ ] Interface gráfica com JavaFX

## Tecnologias utilizadas

- **Java** — linguagem principal, com foco em POO
- **JDBC** — conexão e manipulação do banco de dados
- **MySQL** — armazenamento dos dados
- **Maven** — gerenciamento de dependências *(ajuste conforme seu projeto)*

## Estrutura do projeto

```
gerenciador-academia/
├── src/
│   ├── model/          # Classes de domínio (Aluno, Plano, Pagamento)
│   ├── dao/             # Classes de acesso a dados (JDBC)
│   ├── controller/       # Controle do Pool de Conexões com Hikari
│   └── Main.java        # Ponto de entrada da aplicação
├── database/
│   └── script.sql       # Script de criação do banco de dados
└── README.md
└── pom.xml             # Gerenciador e configurações de depêndencias do Maven
```

## Pré-requisitos

- Java 17 ou superior
- MySQL 8.0 ou superior
- Maven (opcional, se o projeto usar)

## Como instalar e executar

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/gerenciador-academia.git
cd gerenciador-academia
```

2. Crie o banco de dados executando o script disponível em `database/script.sql`.

3. Configure a conexão com o banco no arquivo `ConnectionFactory.java` (ou equivalente):
```java
String url = "jdbc:mysql://localhost:3306/academia";
String user = "root";
String senha = "sua_senha";
```

4. Compile o projeto com o Maven:
```bash
mvn clean compile
```

5. Execute os testes (se houver):
```bash
mvn test
```

6. Gere o arquivo `.jar` executável:
```bash
mvn clean package
```

7. Rode a aplicação:
```bash
java -jar target/Gerenciador-Academia-1.0-SNAPSHOT.jar
```

## Exemplo de uso

```java
Aluno aluno = new Aluno("João Silva", "123.456.789-00", planoMensal);
alunoDAO.salvar(aluno);
```

## Roadmap

- Adicionar interface gráfica
- Implementar geração de relatórios
- Adicionar testes unitários com JUnit

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma *issue* ou enviar um *pull request*.

## Autor

Feito por **Suezin** — [GitHub](https://github.com/Suezin)
