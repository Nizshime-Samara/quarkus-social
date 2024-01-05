package org.estudos.nizshime.camada_de_dominio.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.estudos.nizshime.camada_de_dominio.model.Post;

import lombok.Data;

@Data
public class PostResponse {
    private String text;
    private LocalDateTime dateTime;
    private String authorPostUserName;

    public PostResponse(String text, LocalDateTime dateTime, String authorPostUserName) {
        this.text = text;
        this.dateTime = dateTime;
        this.authorPostUserName = authorPostUserName;
    }
}
