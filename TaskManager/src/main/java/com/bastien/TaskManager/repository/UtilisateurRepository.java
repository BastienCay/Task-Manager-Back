package com.bastien.TaskManager.repository;

import com.bastien.TaskManager.model.Tache;
import com.bastien.TaskManager.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
