package com.bastien.TaskManager.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Entite permettant la representation d'une addresse indiquant le lieu ou se situe une formation
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Compte compte;

    @ManyToMany(mappedBy = "listUtilisateur")
    private List<Tache> listTache;

}
