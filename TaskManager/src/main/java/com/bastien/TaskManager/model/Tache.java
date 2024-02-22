package com.bastien.TaskManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tache implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;


    @Size(max = 512)
    private String description;


    @Size(max = 512)
    private String commentaire;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Etat etat;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priorite priorite;


    private boolean isArchive;

    @ManyToMany
    @JoinTable(name = "assoc_utilisateurs_taches",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "tache_id"))
    private List<Utilisateur> listUtilisateur;



}
