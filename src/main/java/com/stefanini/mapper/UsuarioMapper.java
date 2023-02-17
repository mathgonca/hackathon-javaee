package com.stefanini.mapper;

import com.stefanini.dto.UsuarioRequest;
import com.stefanini.dto.UsuarioResponse;
import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioMapper {
    public UsuarioResponse usuarioToUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getDataNascimento(),
                usuario.getDataCriacao(),
                usuario.getDataAtualizacao());
    }

    public Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest) {
        return new Usuario(usuarioRequest.getNome(),
                usuarioRequest.getLogin(),
                usuarioRequest.getEmail(),
                usuarioRequest.getDataNascimento());
    }

    public Usuario usuarioToUsuarioAtualizado(Usuario usuario, UsuarioRequest usuarioRequest) {
        Usuario usuarioAtualizado = usuarioRequestToUsuario(usuarioRequest);
        usuarioAtualizado.setId(usuario.getId());
        usuarioAtualizado.setDataCriacao(usuario.getDataCriacao());

        return usuarioAtualizado;
    }
}
