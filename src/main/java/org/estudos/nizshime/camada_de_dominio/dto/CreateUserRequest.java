package org.estudos.nizshime.camada_de_dominio.dto;

public class CreateUserRequest {
    private String nome;
    private Integer idade;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    // Construtores
    public CreateUserRequest(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public CreateUserRequest() {
    }

}
