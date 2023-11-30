package br.com.unipe.projeto.ProjetoFinal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Palestrante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nome;

    private String identificacao; // Identificação do palestrante

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "palestrante_palestra",
            joinColumns = @JoinColumn(name = "palestrante_id"),
            inverseJoinColumns = @JoinColumn(name = "palestra_id")
    )
    private List<Palestra> palestras;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "palestrante", cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    @ManyToOne
    private Endereco endereco;
}
