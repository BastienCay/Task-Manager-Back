package com.bastien.TaskManager.repository;

import com.bastien.TaskManager.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {

    @Query(value = "SELECT t FROM Tache t " +
            "WHERE t.isArchive = false " +
            "AND t.id = :idTache")
    Optional<Tache> getTacheActive(@Param("idTache") Long idTache);

    @Query("SELECT t FROM Tache t " +
            "WHERE t.isArchive = false ")
    List<Tache> getAllTachesActives();

}
