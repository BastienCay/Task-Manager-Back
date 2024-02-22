package com.bastien.TaskManager.service.serviceImpl;

import com.bastien.TaskManager.dto.TacheDTO;
import com.bastien.TaskManager.exception.NotFoundException;
import com.bastien.TaskManager.model.Tache;
import com.bastien.TaskManager.model.Utilisateur;
import com.bastien.TaskManager.repository.TacheRepository;
import com.bastien.TaskManager.repository.UtilisateurRepository;
import com.bastien.TaskManager.service.TacheService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TacheServiceImpl implements TacheService {

    private final TacheRepository tacheRepository;
    private final UtilisateurRepository utilisateurRepository;

    public TacheServiceImpl(TacheRepository tacheRepository, UtilisateurRepository utilisateurRepository) {
        this.tacheRepository = tacheRepository;
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public void creerTache(TacheDTO tacheDTO) {
        Tache tache = new Tache();

        tache.setNom(tacheDTO.getNom());
        if (tacheDTO.getDescription() != null) {
            tache.setDescription(tacheDTO.getDescription());
        }
        if (tacheDTO.getCommentaire() != null) {
            tache.setCommentaire(tacheDTO.getCommentaire());
        }
        tache.setPriorite(tacheDTO.getPriorite());
        tache.setEtat(tacheDTO.getEtat());
        tache.setArchive(false);

        try{
            tacheRepository.save(tache);
        } catch(Exception e) {
            throw e;
        }

    }

    @Override
    public Tache assignerTache (Long idTache, Long idUtilisateur) {
        Optional<Tache> optTache = tacheRepository.findById(idTache);
        if (optTache.isEmpty()) {
            throw new NotFoundException("Aucune tache trouvée pour l'id " + idTache);
        }
        Tache tache = optTache.get();

        Optional<Utilisateur> optUtilisateur = utilisateurRepository.findById(idUtilisateur);
        if (optUtilisateur.isEmpty()) {
           throw new NotFoundException("id utilisateur inexistant : " + idUtilisateur);
        }

        Utilisateur utilisateur = optUtilisateur.get();
        if (!utilisateur.getListTache().contains(tache)) {
            tache.getListUtilisateur().add(optUtilisateur.get());
            tacheRepository.save(tache);
        }

        return tache;
    }

    public Tache achiverTache (Long idTache) {
        Optional<Tache> optTache = tacheRepository.getTacheActive(idTache);
        if (optTache.isEmpty()) {
            throw new NotFoundException("Aucune tache trouvée pour l'id " + idTache);
        }
        Tache tache = optTache.get();
        tache.setArchive(true);
        tacheRepository.save(tache);

        return tache;
    }

    public List<TacheDTO> afficherTachesActives () {
       List<Tache> listTaches = tacheRepository.getAllTachesActives();
       List<TacheDTO> listDTO = new ArrayList<>();
       for (Tache tache: listTaches) {
           TacheDTO dto = new TacheDTO();
           dto.setNom(tache.getNom());
           dto.setDescription(tache.getDescription());
           dto.setCommentaire(tache.getCommentaire());
           dto.setEtat(tache.getEtat());
           dto.setPriorite(tache.getPriorite());
           listDTO.add(dto);
       }
       return listDTO;
    }
}
