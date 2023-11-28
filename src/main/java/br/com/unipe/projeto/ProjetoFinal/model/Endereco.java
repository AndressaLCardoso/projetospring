package br.com.unipe.projeto.ProjetoFinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String cep;
    private String bairro;
    private String logradouro;
    private String complemento;
    private String localidade;
    private String uf;
    private String numero;

//    @OneToMany(mappedBy = "endereco")
//    private List<Pessoa> pessoa;
}
