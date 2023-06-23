package com.br.projeto1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projeto1.Model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Integer> {
    Papel findByNome(String nome);
}
