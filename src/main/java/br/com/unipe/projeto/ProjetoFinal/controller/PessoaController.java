package br.com.unipe.projeto.ProjetoFinal.controller;

import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import br.com.unipe.projeto.ProjetoFinal.model.dto.PessoaDTO;
import br.com.unipe.projeto.ProjetoFinal.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodos(){
        List<Pessoa> pessoasList = repository.findAll();
        return ResponseEntity.status(200).body(pessoasList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable("id") Integer pessoaId){

        Optional pessoaPorId = repository.findById(pessoaId);

        if(pessoaPorId.isPresent()){
            Pessoa entidade = (Pessoa) pessoaPorId.get();
            return ResponseEntity.status(200).body(entidade);
        }

        return ResponseEntity.status(204).body(null);
    }

    @GetMapping("/por-cpf/{cpf}")
    public ResponseEntity<Pessoa> buscarPorCpf(@PathVariable("cpf") String cpf){

        Pessoa pessoaPorCpf = repository.findByCpf(cpf);

        if(pessoaPorCpf != null){
            return ResponseEntity.status(200).body(pessoaPorCpf);
        }

        return ResponseEntity.status(204).body(null);
    }

    @PostMapping
    public ResponseEntity<?> criarNovaPessoa(@RequestBody PessoaDTO dto){

        Pessoa pessoaTemp = repository.findByCpf(dto.getCpf());
        System.out.println("pessoaTemp: "+pessoaTemp);

        if(pessoaTemp != null){
            return ResponseEntity.status(406).body("Esse CPF já está cadastrado.");
        }

        Pessoa entidade = new Pessoa();
        entidade.setNome(dto.getNome());
        entidade.setSexo(dto.getSexo());
        entidade.setCpf(dto.getCpf());

        repository.save(entidade);

        return ResponseEntity.status(201).body("Cadastro de "+ entidade.getNome() + " realizado com sucesso.");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Integer pessoaId, @RequestBody PessoaDTO dto){

        Optional pessoaPorId = repository.findById(pessoaId);

        if(pessoaPorId.isPresent()){
            Pessoa entidade = (Pessoa) pessoaPorId.get();
            entidade.setNome(dto.getNome());
            entidade.setSexo(dto.getSexo());
            entidade.setCpf(dto.getCpf());

            repository.save(entidade);
            return ResponseEntity.status(200).body("Pessoa "+ entidade.getNome() + " editada com sucesso.");
        }

        return ResponseEntity.status(404).body("Não há registro com esse id.");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Integer pessoaId){
        System.out.println("Chegou no deletar");
        Optional pessoaPorId = repository.findById(pessoaId);

        if(pessoaPorId.isPresent()){
            Pessoa entidade = (Pessoa) pessoaPorId.get();
            repository.delete(entidade);
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping("/deletar/por-cpf/{cpf}")
    public ResponseEntity<Pessoa> deletarPorCpf(@PathVariable("cpf") String cpf){

        Pessoa pessoaPorCpf = repository.findByCpf(cpf);

        if(pessoaPorCpf != null){
            repository.delete(pessoaPorCpf);
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping("/deletar/todos")
    public ResponseEntity<Pessoa> deletarTodos(){

        List<Pessoa> pessoaList = repository.findAll();

        repository.deleteAll(pessoaList);

        return ResponseEntity.status(204).body(null);
    }
}

