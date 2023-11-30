package br.com.unipe.projeto.ProjetoFinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Palestra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nomePalestra;

    @JsonBackReference
    @ManyToMany(mappedBy = "palestras")
    private List<Pessoa> palestrantes;

    // Outros atributos e métodos conforme necessário
}
