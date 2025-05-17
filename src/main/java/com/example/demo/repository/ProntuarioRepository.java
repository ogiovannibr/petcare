package com.example.demo.repository;

import com.example.demo.model.Animal;
import com.example.demo.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    @Query("SELECT p FROM Prontuario p WHERE p.animal = :animal")
    Optional<Prontuario> findByAnimal(@Param("animal") Animal animal);
    
    @Query("SELECT p FROM Prontuario p WHERE p.animal.id = :animalId")
    Optional<Prontuario> findByAnimalId(@Param("animalId") Long animalId);
}