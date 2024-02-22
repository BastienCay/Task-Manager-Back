package com.bastien.TaskManager.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompteDTO {

    private String identifiant;

    private String motDePasse;

}
