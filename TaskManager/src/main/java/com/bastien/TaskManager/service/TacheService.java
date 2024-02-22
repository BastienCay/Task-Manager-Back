package com.bastien.TaskManager.service;

import com.bastien.TaskManager.dto.TacheDTO;
import com.bastien.TaskManager.model.Tache;

import java.util.List;

public interface TacheService {

    public void creerTache(TacheDTO tacheDTO);

    public Tache assignerTache (Long idTache, Long idUtilisateur);
    public Tache achiverTache (Long idTache);

    public List<TacheDTO> afficherTachesActives ();
}
