package br.com.unipe.projeto.ProjetoFinal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PalestraDTO {

    private String nomePalestra;
    private List<Integer> idPalestrantes;

}
