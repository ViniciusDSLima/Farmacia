package com.Aplicacao.Loja.controller;

import com.Aplicacao.Loja.DTO.funcionario.DadosAtualizarFuncionario;
import com.Aplicacao.Loja.DTO.funcionario.DadosCadastroFuncionario;
import com.Aplicacao.Loja.DTO.funcionario.FuncionarioDTO;
import com.Aplicacao.Loja.domain.funcionario.Funcionario;
import com.Aplicacao.Loja.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarFuncionario(@RequestBody @Valid DadosCadastroFuncionario dadosCadastroFuncionario, UriComponentsBuilder uriComponentsBuilder){
        var funcionario = funcionarioRepository.save(new Funcionario(dadosCadastroFuncionario));

        var uri = uriComponentsBuilder.path("funcionario/{id}").buildAndExpand(funcionario).toUri();

        return ResponseEntity.created(uri).body(new FuncionarioDTO(funcionario));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarFuncionario(@RequestBody @Valid DadosAtualizarFuncionario dadosAtualizarFuncionario){
        var funcionario = funcionarioRepository.getReferenceById(dadosAtualizarFuncionario.id());
        funcionario.atualizarInformacoes(dadosAtualizarFuncionario);

        return ResponseEntity.ok().body(funcionario);
    }
    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> buscarFuncionarios(@PageableDefault(size = 10, sort = {"registro"})Pageable pageable){
        var funcionarios = funcionarioRepository.findAllByAtivoTrue(pageable).map(FuncionarioDTO::new);

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("{id}")
    public ResponseEntity buscarFuncionarioById(@PathVariable Long id){
        var funcionario = funcionarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new FuncionarioDTO(funcionario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity apagarFuncionario(@PathVariable Long id){
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.excluir();

        return ResponseEntity.notFound().build();
    }

}
