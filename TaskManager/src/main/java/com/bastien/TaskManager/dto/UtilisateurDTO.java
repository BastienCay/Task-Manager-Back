package com.bastien.TaskManager.dto;

import com.bastien.TaskManager.model.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UtilisateurDTO {

    private String nom;

    private String prenom;

    private Role role;

}
