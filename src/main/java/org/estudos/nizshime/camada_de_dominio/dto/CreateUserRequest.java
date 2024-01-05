package org.estudos.nizshime.camada_de_dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserRequest {

    @NotBlank(message = "Campo Name obrigatório") // jakarta
    private String name;
    @NotNull(message = "Campo numérico obrigatório") // jakarta
    private Integer age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // Construtores
    public CreateUserRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public CreateUserRequest() {
    }

}
