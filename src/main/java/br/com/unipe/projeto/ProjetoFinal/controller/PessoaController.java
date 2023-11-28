package br.com.unipe.projeto.ProjetoFinal.controller;

import br.com.unipe.projeto.ProjetoFinal.model.Endereco;
import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import br.com.unipe.projeto.ProjetoFinal.model.Telefone;
import br.com.unipe.projeto.ProjetoFinal.model.dto.PessoaDTO;
import br.com.unipe.projeto.ProjetoFinal.repository.EnderecoRepository;
import br.com.unipe.projeto.ProjetoFinal.repository.PessoaRepository;
import br.com.unipe.projeto.ProjetoFinal.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodos(){
        List<Pessoa> pessoasList = pessoaRepository.findAll();
        return ResponseEntity.status(200).body(pessoasList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable("id") Integer pessoaId){

        Optional pessoaPorId = pessoaRepository.findById(pessoaId);

        if(pessoaPorId.isPresent()){
            Pessoa entidade = (Pessoa) pessoaPorId.get();
            return ResponseEntity.status(200).body(entidade);
        }

        return ResponseEntity.status(204).body(null);
    }

    @GetMapping("/por-cpf/{cpf}")
    public ResponseEntity<Pessoa> buscarPorCpf(@PathVariable("cpf") String cpf){

        Pessoa pessoaPorCpf = pessoaRepository.findByCpf(cpf);

        if(pessoaPorCpf != null){
            return ResponseEntity.status(200).body(pessoaPorCpf);
        }

        return ResponseEntity.status(204).body(null);
    }

    @PostMapping
    public ResponseEntity<?> criarNovaPessoa(@RequestBody PessoaDTO dto){

        Pessoa pessoaTemp = pessoaRepository.findByCpf(dto.getCpf());
        System.out.println("pessoaTemp: "+pessoaTemp);

        if(pessoaTemp != null){
            return ResponseEntity.status(406).body("Esse CPF já está cadastrado.");
        }

        Pessoa entidade = new Pessoa();
        entidade.setNome(dto.getNome());
        entidade.setSexo(dto.getSexo());
        entidade.setCpf(dto.getCpf());
        pessoaRepository.save(entidade);

        Endereco endereco = dto.getEndereco();
        enderecoRepository.save(endereco);

        List<Telefone> telefones = dto.getTelefones();
        for(Telefone f: telefones){
            f.setPessoa(entidade);
            telefoneRepository.save(f);
        }

        entidade.setEndereco(endereco);
        entidade.setTelefones(telefones);

        pessoaRepository.save(entidade);

        return ResponseEntity.status(201).body("Cadastro de "+ entidade.getNome() + " realizado com sucesso.");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Integer pessoaId, @RequestBody PessoaDTO dto){

        Optional pessoaPorId = pessoaRepository.findById(pessoaId);

        if(pessoaPorId.isPresent()){
            Pessoa entidade = (Pessoa) pessoaPorId.get();
            entidade.setNome(dto.getNome());
            entidade.setSexo(dto.getSexo());
            entidade.setCpf(dto.getCpf());
            pessoaRepository.save(entidade);

            Endereco endereco = entidade.getEndereco();
            endereco.setCep(dto.getEndereco().getCep());
            endereco.setBairro(dto.getEndereco().getBairro());
            endereco.setLocalidade(dto.getEndereco().getLocalidade());
            endereco.setLogradouro(dto.getEndereco().getLogradouro());
            endereco.setComplemento(dto.getEndereco().getComplemento());
            endereco.setUf(dto.getEndereco().getUf());
            endereco.setNumero(dto.getEndereco().getNumero());
            enderecoRepository.save(endereco);

            List<Telefone> telefones = entidade.getTelefones();

            for(int i = 0; i< telefones.size(); i++){
                Telefone t = telefones.get(i);
                t.setPessoa(entidade);
                t.setNumero(dto.getTelefones().get(i).getNumero());
                telefoneRepository.save(t);
            }

            entidade.setEndereco(endereco);
            entidade.setTelefones(telefones);

            pessoaRepository.save(entidade);
            return ResponseEntity.status(200).body("Pessoa "+ entidade.getNome() + " editada com sucesso.");
        }

        return ResponseEntity.status(404).body("Não há registro com esse id.");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Integer pessoaId){
        System.out.println("Chegou no deletar");
        Optional pessoaPorId = pessoaRepository.findById(pessoaId);

        if(pessoaPorId.isPresent()){
            Pessoa entidade = (Pessoa) pessoaPorId.get();
            pessoaRepository.delete(entidade);
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping("/deletar/por-cpf/{cpf}")
    public ResponseEntity<Pessoa> deletarPorCpf(@PathVariable("cpf") String cpf){

        Pessoa pessoaPorCpf = pessoaRepository.findByCpf(cpf);

        if(pessoaPorCpf != null){
            pessoaRepository.delete(pessoaPorCpf);
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping("/deletar/todos")
    public ResponseEntity<Pessoa> deletarTodos(){

        List<Pessoa> pessoaList = pessoaRepository.findAll();

        pessoaRepository.deleteAll(pessoaList);

        return ResponseEntity.status(204).body(null);
    }
}

