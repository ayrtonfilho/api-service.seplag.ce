# API Spring Boot com SQLite
                      
Esta é uma API Spring Boot. A API fornece recursos para realizar consultas diversas e personalizadas de modo seguro para operações CRUD na tabela pessoa.
                                  
## Design MVC (Model-View-Controller)

A API Spring Boot segue o padrão de arquitetura MVC (Model-View-Controller), que é uma abordagem de design amplamente utilizada para desenvolver aplicativos web. Aqui está uma visão geral de cada componente:

- **Model**: Representa os dados e a lógica de negócios da aplicação. Inclui classes de entidade, repositórios e serviços que lidam com a manipulação dos dados.
- **View**: Responsável pela apresentação da interface do usuário. Em uma API RESTful, as respostas JSON servidas pelos controladores podem ser consideradas a "view".
- **Controller**: Responsável por receber as solicitações do cliente, processar os dados necessários e retornar as respostas apropriadas. Os controladores fazem a ponte entre as solicitações HTTP e a lógica de negócios da aplicação.

O design MVC ajuda a separar as preocupações e a manter o código organizado e modular. Isso facilita a manutenção e a evolução da aplicação ao longo do tempo.
                        
## Modelagem
![image](https://github.com/ayrtonfilho/api-service.seplag.ce/assets/71043862/e29baa6b-9364-4189-938c-fcef4b0f54f6)
                
## Configurações                        
                                
Para garantir o funcionamento correto da aplicação, certifique-se de configurar corretamente os seguintes arquivos de propriedades:

### application.properties
      
```properties
spring.application.name=api-service.seplag.ce

# Configurações do banco de dados
spring.datasource.url=jdbc:sqlite:/caminho/para/o/seu/banco/de/dados/course.api.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
hibernate.show_sql=true
```

### Configurações de CORS
```properties
cors.key_private=f31a841c3e5b14d57f45cdc0ae740ce454952a500c64ad11103af1cb9e7d972e35017645002bc2e4066a243bcd3238f5cafd7e3728afc44bda436c4c0ddba7e2
```
Certifique-se de substituir ```/caminho/para/o/seu/banco/de/dados/ pelo caminho``` real para o banco de dados SQLite.
