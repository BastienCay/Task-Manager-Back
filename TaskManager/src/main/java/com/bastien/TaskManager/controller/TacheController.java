package com.bastien.TaskManager.controller;

import com.bastien.TaskManager.dto.TacheDTO;
import com.bastien.TaskManager.model.Etat;
import com.bastien.TaskManager.model.Priorite;
import com.bastien.TaskManager.model.Tache;
import com.bastien.TaskManager.service.AuthentificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import com.bastien.TaskManager.service.TacheService;

@RestController
@RequestMapping("/taches")
@CrossOrigin
public class TacheController {

    private final TacheService tacheService;
    private final AuthentificationService authentificationService;

    public TacheController(TacheService tacheService, AuthentificationService authentificationService) {
        this.tacheService = tacheService;
        this.authentificationService = authentificationService;
    }

    // Api pour ajouter une tâche
    @PostMapping("/add")
    public void creerTache (@RequestBody TacheDTO tacheDTO) {
        Objects.requireNonNull(tacheDTO.getNom(), "Le nom de la tâche ne peut pas être null");

        //Gestion d'une valeur par défaut si l'état de la tâche est null
        if (tacheDTO.getEtat() == null) {
            tacheDTO.setEtat(Etat.A_FAIRE);
        }
        //Gestion d'une valeur par défaut si la priorité de la tâche est null
        if (tacheDTO.getPriorite() == null) {
            tacheDTO.setPriorite(Priorite.FAIBLE);
        }

        tacheService.creerTache(tacheDTO);
    }

//Méthode pour assigner une tâche à un utilisateur avec une gestion de droits via le header de la requête
    @PostMapping("/assign-task/{idTache}")
    public ResponseEntity<Tache> assignerTache (@PathVariable Long idTache, @RequestParam Long idUtilisateur, HttpServletRequest request) {

        //Récupération de l'id de la personne qui assigne la tâche dans le header de la requête
        Long idUser = Long.valueOf(request.getHeader("idUser"));

        //Un employé peut s'auto assigner une tâche, mais il n'a pas les droits pour assigner des tâches aux autres, à l'inverse des Managers et Admins
        if(idUser == idUtilisateur || !authentificationService.isEmploye(idUser)) {
            tacheService.assignerTache(idTache, idUtilisateur);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    // Api pour archiver une tâche
    @PostMapping("/archive-task/{idTache}")
    public ResponseEntity<Tache> achiverTache (@PathVariable Long idTache) {

        tacheService.achiverTache(idTache);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public List<TacheDTO> afficherTachesActives () {

        return tacheService.afficherTachesActives();
    }

}
