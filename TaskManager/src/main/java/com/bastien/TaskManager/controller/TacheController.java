package com.bastien.TaskManager.controller;

import com.bastien.TaskManager.dto.TacheDTO;
import com.bastien.TaskManager.model.Etat;
import com.bastien.TaskManager.model.Priorite;
import com.bastien.TaskManager.model.Tache;
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

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }


    @PostMapping("/add")
    public void creerTache (@RequestBody TacheDTO tacheDTO) {
        Objects.requireNonNull(tacheDTO.getNom(), "Le nom de la tâche ne peut pas être null");

        if (tacheDTO.getEtat() == null) {
            tacheDTO.setEtat(Etat.A_FAIRE);
        }
        if (tacheDTO.getPriorite() == null) {
            tacheDTO.setPriorite(Priorite.FAIBLE);
        }

        tacheService.creerTache(tacheDTO);
    }

    @PostMapping("/assign-task/{idTache}")
    public ResponseEntity<Tache> assignerTache (@PathVariable Long idTache, @RequestParam Long idUtilisateur) {

        tacheService.assignerTache(idTache, idUtilisateur);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

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
