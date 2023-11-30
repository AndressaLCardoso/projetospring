package br.com.unipe.projeto.ProjetoFinal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PalestranteDTO {

    private String nome;
    private String identificacao;
    private List<String> numerosContato; // Lista de números de contato associados ao palestrante

}