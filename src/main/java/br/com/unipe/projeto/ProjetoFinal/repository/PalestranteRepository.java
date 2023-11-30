package br.com.unipe.projeto.ProjetoFinal.repository;

import br.com.unipe.projeto.ProjetoFinal.model.Palestrante;
import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Integer> {
    Optional<Palestrante> findById(Integer idPalestrante);

//    void save(Palestrante palestrante);
}
