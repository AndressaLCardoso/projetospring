package br.com.unipe.projeto.ProjetoFinal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private String nome;

    private String sexo;

    private String cpf;
}
