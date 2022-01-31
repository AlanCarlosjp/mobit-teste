package com.mobit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String sobreNome;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String cpf;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Endereco> enderecos = new ArrayList<>();
	
}
