package br.com.unipe.projeto.ProjetoFinal.repository;

import br.com.unipe.projeto.ProjetoFinal.model.Endereco;
import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
