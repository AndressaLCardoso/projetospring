package br.com.unipe.projeto.ProjetoFinal.repository;

import br.com.unipe.projeto.ProjetoFinal.model.Palestrante;

import java.util.Optional;

public interface PalestranteRepository {
    Optional<Palestrante> findById(Integer idPalestrante);

    void save(Palestrante palestrante);
}
