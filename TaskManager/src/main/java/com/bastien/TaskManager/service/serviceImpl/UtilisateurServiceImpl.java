package com.bastien.TaskManager.service.serviceImpl;

import com.bastien.TaskManager.dto.UtilisateurDTO;
import com.bastien.TaskManager.exception.NotFoundException;
import com.bastien.TaskManager.model.Compte;
import com.bastien.TaskManager.model.Utilisateur;
import com.bastien.TaskManager.repository.CompteRepository;
import com.bastien.TaskManager.repository.UtilisateurRepository;
import com.bastien.TaskManager.service.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final CompteRepository compteRepository;
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(CompteRepository compteRepository, UtilisateurRepository utilisateurRepository) {
        this.compteRepository = compteRepository;
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public void creerUtilisateur(UtilisateurDTO utilisateurDTO, Long idCompte) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setRole(utilisateurDTO.getRole());

        Optional<Compte> optCompte = compteRepository.findById(idCompte);

        if (!optCompte.isEmpty()){
            Compte compte = optCompte.get();
            utilisateur.setCompte(compte);
            utilisateurRepository.save(utilisateur);
        }else {
            throw new NotFoundException("Aucun compte trouvé pour l'id "+ idCompte);
        }

    }
}
