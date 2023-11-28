package br.com.unipe.projeto.ProjetoFinal.model.dto;

import br.com.unipe.projeto.ProjetoFinal.model.Endereco;
import br.com.unipe.projeto.ProjetoFinal.model.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private String nome;

    private String sexo;

    private String cpf;

    private List<Telefone> telefones;

    private Endereco endereco;

}
