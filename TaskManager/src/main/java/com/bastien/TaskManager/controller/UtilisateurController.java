package com.bastien.TaskManager.controller;

import com.bastien.TaskManager.dto.UtilisateurDTO;
import com.bastien.TaskManager.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    //Création d'un utilisateur une fois que le compte a été créé
    @PostMapping("/add/{idCompte}")
    public void creerUtilisateur (@RequestBody UtilisateurDTO utilisateurDto, @PathVariable Long idCompte) {
        Objects.requireNonNull(utilisateurDto.getNom(), "Le nom ne peut pas être vide");
        Objects.requireNonNull(utilisateurDto.getPrenom(), "Le prenom ne peut pas être vide");
        Objects.requireNonNull(utilisateurDto.getRole(), "Le role ne peut pas être vide");

        utilisateurService.creerUtilisateur(utilisateurDto, idCompte);

    }


}
