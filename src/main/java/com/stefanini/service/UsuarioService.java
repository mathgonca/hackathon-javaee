package com.stefanini.service;

import com.stefanini.dao.UsuarioDAO;
import com.stefanini.dto.UsuarioRequest;
import com.stefanini.dto.UsuarioResponse;
import com.stefanini.mapper.UsuarioMapper;
import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.stefanini.utils.Mensagem.USUARIO_FIND_ERROR;
import static com.stefanini.utils.Mensagem.USUARIO_LOGIN_TAKEN_ERROR;

@ApplicationScoped
public class UsuarioService {
    @Inject
    private UsuarioDAO dao;

    @Inject
    private UsuarioMapper usuarioMapper;

    public void salvar(UsuarioRequest usuarioRequest) {
        verificarLoginEmUso(usuarioRequest.getLogin());

        String senhaEncode = Base64.getEncoder().encodeToString(usuarioRequest.getSenha().getBytes());

        Usuario usuario = usuarioMapper.usuarioRequestToUsuario(usuarioRequest);
        usuario.setSenha(senhaEncode);

        dao.save(usuario);
    }

    public UsuarioResponse atualizar(long id, UsuarioRequest usuarioRequest) {
        Usuario usuario = findById(id);

        if (!usuario.getLogin().equals(usuarioRequest.getLogin())) {
            verificarLoginEmUso(usuarioRequest.getLogin());
        }

        String senhaEncode = Base64.getEncoder().encodeToString(usuarioRequest.getSenha().getBytes());

        Usuario usuarioAtualizado = usuarioMapper.usuarioToUsuarioAtualizado(usuario, usuarioRequest);
        usuarioAtualizado.setSenha(senhaEncode);

        Usuario usuarioSalvo = dao.update(usuarioAtualizado);

        return usuarioMapper.usuarioToUsuarioResponse(usuarioSalvo);
    }

    public void remover(Long id) {
        findById(id);
        dao.delete(id);
    }

    public List<UsuarioResponse> getList() {
        List<Usuario> usuarios = dao.listAll();
        return usuarios.stream()
                .map(usuario -> usuarioMapper.usuarioToUsuarioResponse(usuario))
                .collect(Collectors.toList());
    }

    public UsuarioResponse encontrar(Long id) {
        Usuario usuario = findById(id);
        return usuarioMapper.usuarioToUsuarioResponse(usuario);
    }

    public Usuario findById(long id) {
        return dao.findOptionalById(id)
                .orElseThrow(() -> new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity(USUARIO_FIND_ERROR)
                        .build()));
    }

    public void verificarLoginEmUso(String login) {
        Optional<Usuario> usuarioOptional = dao.findByLogin(login);

        if (usuarioOptional.isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(USUARIO_LOGIN_TAKEN_ERROR)
                    .build());
        }
    }

    public List<UsuarioResponse> listarAniversariantesPorMes(int mesNumero) {
        List<Usuario> usuarios = dao.findByDataNascimentoByMes(mesNumero);
        return usuarios.stream()
                .map(usuario -> usuarioMapper.usuarioToUsuarioResponse(usuario))
                .collect(Collectors.toList());
    }

    public List<String> listProvedoresDeEmail() {
        return dao.findProvedoresDeEmail();
    }

    public List<UsuarioResponse> findByNameStartsWith(String nome) {
        List<Usuario> usuarios = dao.findByNomeStartsWith(nome);
        return usuarios.stream()
                .map(usuario -> usuarioMapper.usuarioToUsuarioResponse(usuario))
                .collect(Collectors.toList());
    }
}
