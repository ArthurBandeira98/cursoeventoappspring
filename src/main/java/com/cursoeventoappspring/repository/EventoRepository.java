package com.cursoeventoappspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursoeventoappspring.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {

	Evento findByCodigo(long codigo);

}
