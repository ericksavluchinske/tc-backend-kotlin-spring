# GitHub Profile Manager

Projeto desenvolvido em Spring Boot com Kotlin que tem como objetivo gerenciar perfis de usuários do GitHub. A aplicação sincroniza dados de usuários diretamente da API pública do GitHub e fornece uma API REST para gerenciamento de perfis e usuários.

-------------------------------------------------------------------------------
## Tecnologias

* **Linguagem:** Kotlin
* **Framework:** Spring Boot
* **Persistência:** Spring Data JPA
* **Banco de Dados:** H2 Database (em memória)
* **Ferramenta de Build:** Gradle

-------------------------------------------------------------------------------

## Funcionalidades implementadas

- [x] **Sincronização de Dados:** Na inicialização da aplicação, um processo automático busca os 30 primeiros usuários da API do GitHub e os persiste no banco de dados.
- [x] **Modelagem de Entidades:** As entidades `User` e `Role` foram modeladas com uma relação `@ManyToMany` para permitir que um usuário tenha múltiplos perfis e um perfil seja atribuído a múltiplos usuários.
- [x] **APIs REST:** As seguintes APIs foram implementadas para gerenciamento de perfis:
    * Criar um novo perfil.
    * Atribuir um perfil a um usuário específico.
    * Listar todos os usuários cadastrados, incluindo os perfis a eles vinculados.
- [x] **Testes Unitários:** O projeto inclui testes para as camadas de `Service` e `Controller`, garantindo a integridade da lógica de negócio e o comportamento dos endpoints.

-------------------------------------------------------------------------------

## Como Executar o Projeto

1.  **Pré-requisito:** IDE compatível com Kotlin e Gradle (como o IntelliJ IDEA).
2.  **Clone o Repositório**
3.  **Configuração do Banco de Dados:** O projeto utiliza o H2 em modo de banco de dados em memória. A configuração é feita no arquivo `src/main/resources/application.properties`.

### Configuração do H2 Database

Para que a aplicação se conecte e crie as tabelas no H2, seu arquivo `application.properties` deve conter as seguintes configurações:

```properties
# ===============================
# Spring Data JPA & Hibernate
# ===============================
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ===============================
# H2 Database
# ===============================
spring.datasource.url=jdbc:h2:mem:githubdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=username
spring.datasource.password=password

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
**Atenção:** Esta configuração é ideal para desenvolvimento e testes. Em um ambiente de produção, utilizar um banco de dados externo e configurar as credenciais como variáveis de ambiente.

4. **Execute a Aplicação:** Abra o projeto no IntelliJ e execute a classe principal. A aplicação estará disponível em ``` http://localhost:8080 ```, e o console do H2 poderá ser acessado em ``` http://localhost:8080/h2-console ```.

-------------------------------------------------------------------------------

## Melhorias

Este projeto pode ser expandido com as seguintes melhorias:

- **DB Migration:** Utilizar ferramentas como Flyway ou Liquibase para gerenciar o esquema do banco de dados de forma versionada.

- **Segurança:** Implementar Autenticação com Token JWT para proteger os endpoints da API.

-------------------------------------------------------------------------------

## Autor - Erick Savluchinske

- GitHub: [https://github.com/ericksavluchinske](https://github.com/ericksavluchinske)

-------------------------------------------------------------------------------