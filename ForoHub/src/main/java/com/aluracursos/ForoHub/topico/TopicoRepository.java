package com.aluracursos.ForoHub.topico;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    ///// esta consulta me permite ordenar de forma ascendente de las fechas de creacion/////
    List<Topico> findAllByOrderByFechaDeCreacionAsc(PageRequest pageRequest);

    ///// esta consulta se utiliza para buscar un topico en especifico /////
    Optional<Topico> findById(Long id);

    boolean existsByTitulo(String titulo);
}

