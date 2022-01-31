package com.mobit.services;

import com.mobit.dto.ContatoDto;
import com.mobit.dto.EnderecoDto;
import com.mobit.entities.Contato;
import com.mobit.entities.Endereco;
import com.mobit.exceptions.DataBaseException;
import com.mobit.exceptions.ResourceNotFoundException;
import com.mobit.ports.repositories.ContatoRepository;
import com.mobit.ports.repositories.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private static final Logger log = LoggerFactory.getLogger(ContatoService.class);

    private final ContatoRepository contatoRepository;
    private final EnderecoRepository enderecoRepository;

    public ContatoService(ContatoRepository contatoRepository,
                          EnderecoRepository enderecoRepository) {
        this.contatoRepository = contatoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public boolean insert(ContatoDto dto) throws Exception {
        log.info("persistindo contato");
        try {
            Contato entity = copyDtoForEntity(dto);
            contatoRepository.save(entity);
            return true;
        } catch (DataBaseException e) {
            log.error("Erro persistir contato, erro do sistema: " + e.getMessage());
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Page<ContatoDto> findAllPaged(PageRequest pageRequest) throws Exception {
        log.info("Retornando lista de contatos");
        try {
            Page<Contato> list = contatoRepository.findAll(pageRequest);
            return list.map(contato -> new ContatoDto(contato));
        } catch (ResourceNotFoundException e) {
            log.error("Erro em retornar lista de contatos, erro do sistema: " + e.getMessage());
        }
        throw new Exception("lista vazia");
    }

    @Transactional(readOnly = true)
    public ContatoDto findById(Long id) {
        log.info("retornando objeto com id: " + id);
        Optional<Contato> entity = contatoRepository.findById(id);
        ContatoDto dto = new ContatoDto(entity.orElseThrow(() -> new ResourceNotFoundException("Entity not exist")));
        return dto;
    }

    @Transactional
    public ContatoDto update(ContatoDto dto) {
        log.info("Update do contato com id: " + dto.getId());
        if (contatoRepository.existsById(dto.getId())) {
            Contato entity = copyDtoForEntity(dto);
            contatoRepository.save(entity);
            ContatoDto reponse = new ContatoDto(entity);
            return reponse;
        }
        throw new EntityNotFoundException("Id nao existe");
    }

    @Transactional
    public void delete(Long id) {
        try {
            contatoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Contato não existe");
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public List<EnderecoDto> findEnderecosById(Long id) {
        log.info("Retornando enderecos do contato com id: " + id);
        try {
            Contato entity = contatoRepository.getById(id);
            List<EnderecoDto> enderecosDto = new ArrayList<>();
            for (Endereco x : entity.getEnderecos()) {
                enderecosDto.add(new EnderecoDto(x));
            }
            return enderecosDto;
        } catch (EmptyResultDataAccessException e) {
            log.error("Lista de enderecos vazia, erro do sistema: " + e.getMessage());
        }
        return null;
    }

    private Contato copyDtoForEntity(ContatoDto dto) {

        //TODO fazer logica para retornar todas as informações com base na api do viaCep

        Contato entity = new Contato();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setSobreNome(dto.getSobreNome());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        for (EnderecoDto x : dto.getEnderecos()) {
            Optional<Endereco> endereco = enderecoRepository.findById(x.getCep());
            enderecoRepository.save(endereco.orElse((new Endereco(x.getCep(), x.getBairro(),
                    x.getUf(), x.getTipoDeEndereco(), x.getLogradouro(), x.getCidade()))));
            entity.getEnderecos().add(endereco.orElse(new Endereco(x.getCep(), x.getBairro(),
                    x.getUf(), x.getTipoDeEndereco(), x.getLogradouro(), x.getCidade())));
        }
        return entity;
    }
}
