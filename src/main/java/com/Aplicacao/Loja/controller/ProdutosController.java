package com.Aplicacao.Loja.controller;

import com.Aplicacao.Loja.DTO.produtos.DadosAtualizarProdutos;
import com.Aplicacao.Loja.DTO.produtos.DadosCadastroProduto;
import com.Aplicacao.Loja.DTO.produtos.ProdutoDTO;
import com.Aplicacao.Loja.domain.produtos.Produtos;
import com.Aplicacao.Loja.repository.ProdutosRepositoru;
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
@RequestMapping("produtos")
public class ProdutosController {
    @Autowired
    private ProdutosRepositoru produtosRepositoru;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarProdutos(@RequestBody @Valid DadosCadastroProduto dadosCadastroProduto, UriComponentsBuilder uriComponentsBuilder){
        var produtos = produtosRepositoru.save(new Produtos(dadosCadastroProduto));

        var uri = uriComponentsBuilder.path("produtos/{id}").buildAndExpand(produtos).toUri();

        return ResponseEntity.created(uri).body(new ProdutoDTO(produtos));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarProduto(@RequestBody @Valid DadosAtualizarProdutos dadosAtualizarProdutos){
        var produto = produtosRepositoru.getReferenceById(dadosAtualizarProdutos.id());
        produto.atualizarInformacoes(dadosAtualizarProdutos);

        return ResponseEntity.ok().body(new ProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> buscarProdutos(@PageableDefault(size = 10, sort = {"descricao"})Pageable pageable){
        var produto = produtosRepositoru.findAllByAtivoTrue(pageable).map(ProdutoDTO::new);

        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarProdutoPorId(@PathVariable Long id){
        var produto = produtosRepositoru.getReferenceById(id);

        return ResponseEntity.ok(new ProdutoDTO(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarProduto(@PathVariable Long id){
        var produto = produtosRepositoru.getReferenceById(id);
        produto.excluir();

        return ResponseEntity.noContent().build();
    }
}
