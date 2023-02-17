package com.stefanini.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.stefanini.utils.MensagemValidacao.*;

public class UsuarioRequest {
    @NotNull(message = NOME_NOT_NULL)
    @NotBlank(message = NOME_NOT_BLANK)
    @Size(max = 50, message = NOME_SIZE)
    private String nome;

    @NotNull(message = LOGIN_NOT_NULL)
    @NotBlank(message = LOGIN_NOT_BLANK)
    @Size(min = 5, max = 20, message = LOGIN_SIZE)
    private String login;

    @NotNull(message = EMAIL_NOT_NULL)
    @NotBlank(message = EMAIL_NOT_BLANK)
    @Size(min = 10, message = EMAIL_SIZE)
    @Email(message = EMAIL_INVALID)
    private String email;

    @NotNull(message = SENHA_NOT_NULL)
    @NotBlank(message = SENHA_NOT_BLANK)
    @Size(min = 4, max = 10, message = SENHA_SIZE)
    private String senha;

    @PastOrPresent(message = DATA_NASCIMENTO_PAST_OR_PRESENTE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String nome, String login, String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
