<p>
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white"/>
</p>

# API Carros - SpringBoot

API consulta carros presentes no banco de dados e atráves dos métodos get,post, put e delete é possível acessar e modificar a API. API também possui níveis de segurança de ADMIN e USER que controlam quem poderá modificar a mesma.Esses níveis de acesso são contolodos pelo JWT token e usuarios e ROLES presentes no banco de dados.Também é possível acessar lista de usuários registrados no banco de dados e suas respectivas ROLES.



## Features

- Inserir Carros
- Alterar Carros
- Consultar Carros
- Autorização e Autenticação de usuário
- Consultar Usuário

## Ferramentas

 - [SpringBoot](https://spring.io/)
 - [MySql](https://www.mysql.com/)


## API-Carros
### Buscar todos os carros
``GET /api/v1/carros``
```json
    {
        "id": 1,
        "nome": "Tucker 1948",
        "tipo": "classicos"
    }
```

### Buscar carro por ID
```GET /api/v1/carros/{id}```

```
{
    "id": 6,
    "nome": "Cadillac Eldorado",
    "tipo": "classicos"
}
```

### Buscar carro por tipo
```GET /api/v1/carros/tipo/{tipo}```
```
{
        "id": 1,
        "nome": "Tucker 1948",
        "tipo": "classicos"
    },
    {
        "id": 2,
        "nome": "Chevrolet Corvette",
        "tipo": "classicos"
    },
    {
        "id": 3,
        "nome": "Chevrolet Impala Coupe",
        "tipo": "classicos"
    }
}
```

### Atualizar carro
```PUT /api/v1/carros/{id} ```

```json
    {
        "nome": "Tucker 1948",
        "tipo": "classicos"
    }
```

* Não é permitido mudar ID apenas as informações
* Só é permitido para usuário com role admin

### Deletar carro

```DELETE /api/v1/carros/{id}```

### Criar novo carro
```POST /api/v1/carros```
```
{
    "nome": "Tucker 1948",
    "tipo": "classicos" 
}
```

## API-Usuário
### Consultar Usuários
```GET /api/v1/userInfo```

```
{
    "nome": "Admin",
    "email": "admin@gmail.com",
    "login": "admin",
    "token": null
}
```
