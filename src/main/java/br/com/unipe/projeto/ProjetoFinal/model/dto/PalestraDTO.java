package br.com.unipe.projeto.ProjetoFinal.model.dto;

import br.com.unipe.projeto.ProjetoFinal.model.Palestra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PalestraDTO {

    private String nomePalestra;
    private List<Integer> palestrantesId;
    private List<Integer> palestrasId;
}
