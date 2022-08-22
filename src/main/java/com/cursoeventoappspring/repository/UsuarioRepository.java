package com.cursoeventoappspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursoeventoappspring.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Usuario findByLogin(String login);

}
