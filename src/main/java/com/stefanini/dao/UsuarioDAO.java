package com.stefanini.dao;

import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
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

    public List<Usuario> findByDataNascimentoByMes(int mesNumero) {
        return em.createQuery("SELECT u FROM Usuario u WHERE MONTH(u.dataNascimento) = :mesNumero", Usuario.class)
                .setParameter("mesNumero", mesNumero).getResultList();
    }

    public List<String> findProvedoresDeEmail() {
        return em.createNativeQuery("SELECT SUBSTRING(u.email, POSITION('@' in u.email) + 1) as provedores from TB_USUARIO u group by provedores")
                .getResultList();
    }
}
