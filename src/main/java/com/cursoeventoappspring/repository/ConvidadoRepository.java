package com.cursoeventoappspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursoeventoappspring.model.Convidado;
import com.cursoeventoappspring.model.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {

	Iterable<Convidado> findByEvento(Evento evento);

	Convidado findByRg(long rg);

}
