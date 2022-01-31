package com.mobit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Endereco {

    @Id
    private String cep;

    private String bairro;
    private String uf;
    private String logradouro;
    private String cidade;
    private String tipoDeEndereco;

    public Endereco(String cep){
        this.cep = cep;
    }
}
