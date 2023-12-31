package br.com.unipe.projeto.ProjetoFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor; //Anotação para construtor completo
import lombok.Data; //Equivalente a getters e setters
import lombok.NoArgsConstructor; //Anotação para construtor vazio

import java.util.List;
//import lombok.Getter; //Métodos getters
//import lombok.Setter; //Métodos setters

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nome;

    private String sexo;

    private String cpf;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa",cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    @ManyToOne
    private Endereco endereco;

}
