package com.mobit.ports.controller;

import com.mobit.dto.ContatoDto;
import com.mobit.dto.EnderecoDto;
import com.mobit.services.ContatoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/contatos")
public class ContatoController {

	private final ContatoService service;

	public ContatoController(ContatoService service) {
		this.service = service;
	}


	@GetMapping
	public ResponseEntity<Page<ContatoDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) throws Exception {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		Page<ContatoDto> list = service.findAllPaged(pageRequest);

		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ContatoDto> findById(@PathVariable Long id) {
		ContatoDto dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	@GetMapping(value = "/{id}/enderecos")
	public ResponseEntity<List<EnderecoDto>> enderecos(@PathVariable Long id) {
		List<EnderecoDto> dto = service.findEnderecosById(id);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity insert(@RequestBody ContatoDto dto) throws Exception {
		boolean inserted = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(inserted);

	}

	@PutMapping
	public ResponseEntity<ContatoDto> update(@RequestBody ContatoDto dto) {
		dto = service.update(dto);
		return ResponseEntity.ok(dto);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ContatoDto> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
