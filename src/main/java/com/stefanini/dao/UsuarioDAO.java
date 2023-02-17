package com.stefanini.dao;

import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UsuarioDAO extends GenericDAO<Usuario, Long> {
    public Optional<Usuario> findOptionalById(long id) {
        return Optional.ofNullable(this.findById(id));
    }

    public Optional<Usuario> findByLogin(String login) {
        return Optional.ofNullable(em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
                .setParameter("login", login).getResultList().stream().findFirst().orElse(null));
    }
}
