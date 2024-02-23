package com.bastien.TaskManager.dto;

import com.bastien.TaskManager.model.Etat;
import com.bastien.TaskManager.model.Priorite;
import lombok.Data;

@Data
public class TacheDTO {

    private String nom;

    private String description;

    private String commentaire;

    private Etat etat;

    private Priorite priorite;

    private boolean isArchive;

}
