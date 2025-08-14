# Sistema de Cadastro de Alunos (Java + JPA)

Este repositório traz um projeto simples para a disciplina **Programação Web 3**, focado no cadastro, consulta, alteração e exclusão de alunos usando Java, Maven e JPA (Hibernate).

## Sobre o Projeto

A aplicação é um sistema de linha de comando que permite gerenciar alunos, armazenando os dados em um banco de dados relacional via JPA. O usuário pode cadastrar, buscar, listar, alterar e excluir alunos, além de visualizar o status de aprovação com base na média das notas.

### Funcionalidades

- **Cadastrar Aluno**: Insere um novo aluno com nome, RA, email e três notas.
- **Excluir Aluno**: Remove um aluno pelo ID.
- **Alterar Aluno**: Atualiza os dados de um aluno existente.
- **Buscar Aluno pelo Nome**: Consulta um aluno pelo nome.
- **Listar Alunos (aprovados)**: Exibe apenas alunos aprovados.
- **Listar Alunos (todos)**: Exibe todos os alunos cadastrados.
- **Status de Aprovação**: Calculado automaticamente (Aprovado, Recuperação ou Reprovado).

## Tecnologias

- **Java 21**
- **Maven**
- **JPA (Hibernate)**
- **H2 Database**

## Estrutura

```
student-management/
├── src/
│   └── main/
│       └── java/
│           └── br/
│               └── edu/
│                   └── ifsp/
│                       └── leo/
│                           ├── Main.java
│                           ├── model/
│                           ├── dao/
│                           ├── service/
│                           ├── persistence/
│                           └── view/
├── pom.xml
├── LICENSE
└── README.md
```

## Observações

- O status do aluno é calculado pela média das três notas:
    - **Aprovado**: média ≥ 6
    - **Recuperação**: média ≥ 4 e < 6
    - **Reprovado**: média < 4
- O banco de dados pode ser alterado via configuração JPA.
- O sistema é totalmente interativo via terminal.

## Exemplos

**Cadastro de Aluno**

```
Nome: Leo Freitas
RA: 123456
Email: leo.freitas@ifsp.edu.br
Nota 1: 10.0
Nota 2: 10.0
Nota 3: 10.0
Aluno cadastrado com sucesso!
```

**Listar Alunos (aprovados)**

```
+----+--------------------------------+----------------------+--------------+--------+--------+--------+-------------+
| ID | Email                         | Nome                  | RA           | Nota 1 | Nota 2 | Nota 3 | Status      |
+----+--------------------------------+----------------------+--------------+--------+--------+--------+-------------+
| 1  | leo.freitas@ifsp.edu.br       | Leo Freitas           | 123456       | 10.0   | 10.0   | 10.0   | APROVADO    |
+----+--------------------------------+----------------------+--------------+--------+--------+--------+-------------+
```

## Créditos

Projeto feito para a disciplina **PRW3 (Programação Web 3)**.