package com.bastien.TaskManager.service.serviceImpl;

import com.bastien.TaskManager.dto.CompteDTO;
import com.bastien.TaskManager.exception.NotFoundException;
import com.bastien.TaskManager.model.Compte;
import com.bastien.TaskManager.repository.CompteRepository;
import com.bastien.TaskManager.service.CompteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;

    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }


    @Override
    public void creerCompte(CompteDTO compteDTO) {
        Compte compte = new Compte();
        compte.setIdentifiant(compteDTO.getIdentifiant());
        compte.setMotDePasse(compteDTO.getMotDePasse());
        try{
            compteRepository.save(compte);
        }catch (RuntimeException exception){
            throw exception;
        }

    }

}
