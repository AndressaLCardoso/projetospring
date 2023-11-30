package br.com.unipe.projeto.ProjetoFinal.controller;

import br.com.unipe.projeto.ProjetoFinal.model.Palestra;
import br.com.unipe.projeto.ProjetoFinal.model.Pessoa;
import br.com.unipe.projeto.ProjetoFinal.model.dto.PalestraDTO;
import br.com.unipe.projeto.ProjetoFinal.repository.PalestraRepository;
import br.com.unipe.projeto.ProjetoFinal.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/palestra")
public class PalestraController {

    @Autowired
    private PalestraRepository palestraRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

//    @Autowired
//    private PalestranteRepository palestranteRepository;

    @GetMapping
    public ResponseEntity<List<Palestra>> listarTodas() {
        List<Palestra> palestrasList = palestraRepository.findAll();
        return ResponseEntity.status(200).body(palestrasList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Palestra> buscarPorId(@PathVariable("id") Integer palestraId) {
        Optional<Palestra> palestraPorId = palestraRepository.findById(palestraId);

        return palestraPorId.map(palestra -> ResponseEntity.status(200).body(palestra))
                .orElseGet(() -> ResponseEntity.status(204).body(null));
    }

    @PostMapping
    public ResponseEntity<?> criarNovaPalestra(@RequestBody PalestraDTO dto) {
        Palestra palestra = new Palestra();
        palestra.setNomePalestra(dto.getNomePalestra());
        palestraRepository.save(palestra);

//        List<Integer> palestrantesId = dto.getPalestrantesId();
//        List<Pessoa> palestrantes = new ArrayList<>();
//        for(Integer i : palestrantesId){
//            Pessoa p = pessoaRepository.findById(i).orElse(null);
//            palestrantes.add(p);
////            p.setPalestras();
//        }
//        palestra.setPalestrantes(palestrantes);
//        palestraRepository.save(palestra);


//        for (Integer idPalestrante : idPalestrantes) {
//            Optional<Pessoa> palestranteOptional = pessoaRepository.findById(idPalestrante);
//            palestranteOptional.ifPresent(palestrante -> {
//                palestra.getPalestrantes().add(palestrante);
//                palestrante.getPalestras().add(palestra);
//                pessoaRepository.save(palestrante);
//            });
//        }

        return ResponseEntity.status(201).body("Cadastro da palestra " + palestra.getNomePalestra() + " realizado com sucesso.");
    }
    @PostMapping("/associar")
    public ResponseEntity<?> associarPalestrantes(@RequestBody PalestraDTO dto) {

        List<Integer> palestrasId = dto.getPalestrasId();
        List<Palestra> palestras = new ArrayList<>();
        for(Integer i : palestrasId){
            Palestra p = palestraRepository.findById(i).orElse(null);
            palestras.add(p);
        }

        List<Integer> palestrantesId = dto.getPalestrantesId();
        List<Pessoa> palestrantes = new ArrayList<>();
        for(Integer i : palestrantesId){
            Pessoa p = pessoaRepository.findById(i).orElse(null);
            palestrantes.add(p);
            p.setPalestras(palestras);
            pessoaRepository.save(p);
        }
        for(int i = 0; i < palestras.size(); i++){
            Palestra p = palestras.get(i);
            p.setPalestrantes(palestrantes);
            palestraRepository.save(p);
        }


        return ResponseEntity.status(201).body("Associação  realizada com sucesso.");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Integer palestraId, @RequestBody PalestraDTO dto) {
        Optional<Palestra> palestraOptional = palestraRepository.findById(palestraId);

        if (palestraOptional.isPresent()) {
            Palestra palestra = palestraOptional.get();
            palestra.setNomePalestra(dto.getNomePalestra());
            palestraRepository.save(palestra);

            // Remova os palestrantes existentes associados à palestra
//            palestra.getPalestrantes().clear();

            List<Integer> idPalestrantes = dto.getPalestrantesId();

//            for (Integer idPalestrante : idPalestrantes) {
//                Optional<Pessoa> palestranteOptional = pessoaRepository.findById(idPalestrante);
//                palestranteOptional.ifPresent(palestrante -> {
//                    palestra.getPalestrantes().add(palestrante);
//                    palestrante.getPalestras().add(palestra);
//                    pessoaRepository.save(palestrante);
//                });
//            }

            return ResponseEntity.status(200).body("Palestra " + palestra.getNomePalestra() + " editada com sucesso.");
        }

        return ResponseEntity.status(404).body("Não há registro com esse id.");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Integer palestraId) {
        Optional<Palestra> palestraOptional = palestraRepository.findById(palestraId);

        if (palestraOptional.isPresent()) {
            Palestra palestra = palestraOptional.get();
            palestraRepository.delete(palestra);
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.status(204).body(null);
    }
}
