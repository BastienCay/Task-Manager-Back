package com.bastien.TaskManager.service;

import com.bastien.TaskManager.dto.CompteDTO;
import com.bastien.TaskManager.model.Compte;

public interface CompteService {

    public void creerCompte(CompteDTO compteDTO);
}
