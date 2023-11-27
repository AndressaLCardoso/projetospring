package br.com.unipe.projeto.ProjetoFinal.repository;

import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Pessoa findByCpf(String cpf);
}
