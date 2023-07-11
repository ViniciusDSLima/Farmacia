package com.Aplicacao.Loja.repository;

import com.Aplicacao.Loja.domain.produtos.Produtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepositoru extends JpaRepository<Produtos, Long> {
    Page<Produtos> findAllByAtivoTrue(Pageable pageable);
}
