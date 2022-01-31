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
