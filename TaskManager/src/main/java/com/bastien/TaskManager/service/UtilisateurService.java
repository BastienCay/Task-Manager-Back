package com.bastien.TaskManager.service;

import com.bastien.TaskManager.dto.UtilisateurDTO;

public interface UtilisateurService {

    public void creerUtilisateur(UtilisateurDTO utilisateurDTO, Long idCompte);

}
