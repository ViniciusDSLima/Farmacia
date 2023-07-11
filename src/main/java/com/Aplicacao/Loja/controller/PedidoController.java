package com.Aplicacao.Loja.controller;

import com.Aplicacao.Loja.DTO.pedidos.DadosAtualizarPedido;
import com.Aplicacao.Loja.DTO.pedidos.DadosCadastroPedido;
import com.Aplicacao.Loja.DTO.pedidos.PedidosDTO;
import com.Aplicacao.Loja.domain.pedidos.Pedidos;
import com.Aplicacao.Loja.repository.PedidosRepository;
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
@RequestMapping("pedidos")
public class PedidoController {
    @Autowired
    private PedidosRepository pedidosRepository;

    @PostMapping("/comprar")
    @Transactional
    public ResponseEntity comprarProduto(@RequestBody @Valid DadosCadastroPedido dadosCadastroPedido, UriComponentsBuilder uriComponentsBuilder){
        var pedido = pedidosRepository.save(new Pedidos(dadosCadastroPedido));

        var uri = uriComponentsBuilder.path("pedidos/{id}").buildAndExpand(pedido).toUri();

        return ResponseEntity.created(uri).body(new PedidosDTO(pedido));
    }

    @PutMapping("/atualizarPedido")
    @Transactional
    public ResponseEntity atualizarProduto(@RequestBody @Valid DadosAtualizarPedido dadosAtualizarPedido){
        var pedido = pedidosRepository.getReferenceById(dadosAtualizarPedido.id());
        pedido.atualzarInformacoes(dadosAtualizarPedido);

        return ResponseEntity.ok().body(new PedidosDTO(pedido));
    }

    @GetMapping
    public ResponseEntity<Page<PedidosDTO>> buscarPedidos(@PageableDefault(size = 10, sort = {"dataPedido"})Pageable pageable){
        var pedidos = pedidosRepository.findAllByAtivoTrue(pageable).map(PedidosDTO::new);

        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPedidoById(@PathVariable Long id){
        var pedido = pedidosRepository.getReferenceById(id);

        return ResponseEntity.ok(new PedidosDTO(pedido));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity apagarPedidoById(@PathVariable Long id){
        var pedido = pedidosRepository.getReferenceById(id);
        pedido.excluir();

        return ResponseEntity.noContent().build();
    }
}
