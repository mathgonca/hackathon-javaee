package com.stefanini.controller;

import com.stefanini.dto.UsuarioRequest;
import com.stefanini.dto.UsuarioResponse;
import com.stefanini.service.UsuarioService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuario")
public class UsuarioController {
    @Inject
    private UsuarioService service;

    @POST
    public Response salvar(@Valid UsuarioRequest usuarioRequest) {
        service.salvar(usuarioRequest);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") long id, @Valid UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = service.atualizar(id, usuarioRequest);
        return Response.status(Response.Status.CREATED).entity(usuarioResponse).build();
    }

    @DELETE
    @Path("{id}")
    public Response remover(@PathParam("id") long id) {
        service.remover(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList() {
        List<UsuarioResponse> usuarioResponses = service.getList();
        return Response.status(Response.Status.OK).entity(usuarioResponses).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrar(@PathParam("id") long id) {
        UsuarioResponse usuarioResponse = service.encontrar(id);
        return Response.status(Response.Status.OK).entity(usuarioResponse).build();
    }

    @GET
    @Path("/aniversariantes/{mes}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response aniversariantesPorMes(@PathParam("mes") int mes) {
        List<UsuarioResponse> usuarioResponses = service.listarAniversariantesPorMes(mes);
        return Response.status(Response.Status.OK).entity(usuarioResponses).build();
    }

    @GET
    @Path("/provedores-de-email")
    @Produces(MediaType.APPLICATION_JSON)
    public Response provedoresDeEmail() {
        List<String> provedores = service.listProvedoresDeEmail();
        return Response.status(Response.Status.OK).entity(provedores).build();
    }
}
