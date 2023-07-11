package com.Aplicacao.Loja.controller;

import com.Aplicacao.Loja.DTO.cliente.ClienteDTO;
import com.Aplicacao.Loja.DTO.cliente.DadosAtualizarCliente;
import com.Aplicacao.Loja.DTO.cliente.DadosCadastroCliente;
import com.Aplicacao.Loja.DTO.funcionario.FuncionarioDTO;
import com.Aplicacao.Loja.domain.cliente.Cliente;
import com.Aplicacao.Loja.repository.ClienteRepository;
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
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarCliente(@RequestBody @Valid DadosCadastroCliente dadosCadastroCliente, UriComponentsBuilder uriComponentsBuilder){
        var cliente = clienteRepository.save(new Cliente(dadosCadastroCliente));

        var uri = uriComponentsBuilder.path("clientes/{id}").buildAndExpand(cliente).toUri();

        return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
    }

    @PutMapping("/atuaizar")
    @Transactional
    public ResponseEntity atualizarCliente(@RequestBody @Valid DadosAtualizarCliente dadosAtualizarCliente){
        var cliente = clienteRepository.getReferenceById(dadosAtualizarCliente.id());
        cliente.atualizarInformacoes(dadosAtualizarCliente);

        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> buscarClientes(@PageableDefault(size = 10, sort = {"nome"})Pageable pageable){
        var cliente = clienteRepository.findAllByAtivoTrue(pageable).map(ClienteDTO::new);

        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarCliente(@PageableDefault Long id){
        var cliente = clienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new ClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarClientre(@PathVariable Long id){
        var cliente = clienteRepository.getReferenceById(id);
        cliente.excluir();

        return ResponseEntity.noContent().build();
    }

}
