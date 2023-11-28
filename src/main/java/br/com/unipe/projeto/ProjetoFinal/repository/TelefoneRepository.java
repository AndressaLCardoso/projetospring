package br.com.unipe.projeto.ProjetoFinal.repository;

import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import br.com.unipe.projeto.ProjetoFinal.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {

}
