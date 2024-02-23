package com.bastien.TaskManager.controller;

import com.bastien.TaskManager.dto.CompteDTO;
import com.bastien.TaskManager.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/comptes")
@CrossOrigin
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }


//Création d'un compte lors d'un premier log sur l'application
@PostMapping("/add")
    public void creerCompte (@RequestBody CompteDTO compteDTO) {

        Objects.requireNonNull(compteDTO.getIdentifiant(), "L'identifiant ne doit pas être vide");
        Objects.requireNonNull(compteDTO.getMotDePasse(), "Le mot de passe ne doit pas être vide");

        compteService.creerCompte(compteDTO);
    }
}
