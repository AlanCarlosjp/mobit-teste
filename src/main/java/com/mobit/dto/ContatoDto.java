package com.mobit.dto;

import com.mobit.entities.Contato;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ContatoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "nome não deve ser vazio")
	private String nome;
	@NotBlank(message = "sobre nome não deve ser vazio")
	private String sobreNome;
	@NotBlank(message = "email não aceito")
	private String email;
	@CPF
	private String cpf;

	private List<EnderecoDto> enderecos = new ArrayList<>();

	public ContatoDto(Contato entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.sobreNome = entity.getSobreNome();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		entity.getEnderecos().stream().map(endereco -> this.enderecos.add(new EnderecoDto(endereco)));
	}
}
