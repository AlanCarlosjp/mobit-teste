package com.mobit.ports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobit.entities.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
