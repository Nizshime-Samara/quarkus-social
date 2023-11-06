package org.estudos.nizshime.camada_de_dominio.dto;

public class CreateUserRequest {
    private String name;
    private Integer idade;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    // Construtores
    public CreateUserRequest(String name, Integer idade) {
        this.name = name;
        this.idade = idade;
    }

    public CreateUserRequest() {
    }

}
