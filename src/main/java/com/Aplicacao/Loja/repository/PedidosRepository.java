package com.Aplicacao.Loja.repository;

import com.Aplicacao.Loja.domain.pedidos.Pedidos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
    Page<Pedidos> findAllByAtivoTrue(Pageable pageable);
}
