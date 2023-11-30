package br.com.unipe.projeto.ProjetoFinal.repository;

import br.com.unipe.projeto.ProjetoFinal.model.Palestra;

import java.util.List;
import java.util.Optional;

public interface PalestraRepository {
    Optional<Palestra> findById(Integer palestraId);

    List<Palestra> findAll();

    void save(Palestra palestra);

    void delete(Palestra palestra);
}
