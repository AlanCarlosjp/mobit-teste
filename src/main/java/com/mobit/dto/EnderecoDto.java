package com.mobit.dto;

import com.mobit.entities.Endereco;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EnderecoDto {

    private String cep;
    private String bairro;
    private String uf;
    private String logradouro;
    private String cidade;
    private String tipoDeEndereco;

    public EnderecoDto(Endereco entity) {
        this.cep = entity.getCep();
        this.bairro = entity.getBairro();
        this.uf = entity.getUf();
        this.logradouro = entity.getLogradouro();
        this.cidade = entity.getCidade();
        this.tipoDeEndereco = entity.getTipoDeEndereco();
    }
    public EnderecoDto(String cep) {
        this.cep = cep;
    }
}
