package com.bastien.TaskManager.service.serviceImpl;

import com.bastien.TaskManager.model.Role;
import com.bastien.TaskManager.model.Utilisateur;
import com.bastien.TaskManager.repository.UtilisateurRepository;
import com.bastien.TaskManager.service.AuthentificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {

    private final UtilisateurRepository utilisateurRepository;

    public AuthentificationServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    //Méthode pour vérifier grace à l'id de l'utilisateur son rôle
    @Override
    public Boolean isEmploye(Long idUtilisateur) {

        Optional<Utilisateur> optUtilisateur = utilisateurRepository.findById(idUtilisateur);

        if (optUtilisateur.get().getRole() == Role.MANAGER || optUtilisateur.get().getRole() == Role.ADMINISTRATEUR) {
            return false;
        }
        return true;
    }

}
