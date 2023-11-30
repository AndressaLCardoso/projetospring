package br.com.unipe.projeto.ProjetoFinal.repository;

import br.com.unipe.projeto.ProjetoFinal.model.Palestra;
import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PalestraRepository extends JpaRepository<Palestra, Integer> {
    Optional<Palestra> findById(Integer palestraId);

    List<Palestra> findAll();

//    void save(Palestra palestra);

    void delete(Palestra palestra);
}
