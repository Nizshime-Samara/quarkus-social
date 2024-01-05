package org.estudos.nizshime.camada_de_dominio.dto;

import org.hibernate.validator.constraints.time.DurationMax;

import lombok.Data;

@Data
public class CreatePostRequest {

    private String text;

}
