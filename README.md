# mobit-teste

# Aplicativo de cadastro de contatos


Entidade formato json: 
contato{
            "id": Long,
            "nome": String,
            "sobreNome": String,
            "email": String,
            "cpf": String,
            "enderecos": [
            {
             "cep": String,
            "bairro": String,
            "uf": String,
            "logradouro": String,
            "cidade": String,
            "tipoDeEndereco":String
            }]
            }
            
# EndPoints

base url do backEnd: https://mobit-teste.herokuapp.com
base url do frontEnd: 

* Post: /api/contatos/
* PUT: /api/contatos/
* CREATE: /api/contatos/
* GET(paginado): /api/contatos/
* DELETE: /api/contatos/{id}
* ENDEREÇOS DO CONTATO: /api/contatos/{id}/enderecos

# Característica

Arquiteruo mvc 
![Captura de ecrã de 2022-01-31 11-26-22](https://user-images.githubusercontent.com/84341683/151811211-1658f71a-9991-48eb-83a7-9f88723c5c4c.png)


