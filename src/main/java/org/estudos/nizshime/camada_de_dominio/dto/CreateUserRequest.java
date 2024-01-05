package org.estudos.nizshime.camada_de_dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Campo Name obrigatório") // jakarta
    private String name;
    @NotNull(message = "Campo numérico obrigatório") // jakarta
    private Integer age;

}
