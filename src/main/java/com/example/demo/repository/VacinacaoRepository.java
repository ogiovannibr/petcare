package com.example.demo.repository;

import com.example.demo.model.Animal;
import com.example.demo.model.Vacinacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
    @Query("SELECT v FROM Vacinacao v WHERE v.animal = :animal")
    List<Vacinacao> findByAnimal(@Param("animal") Animal animal);
    
    @Query("SELECT v FROM Vacinacao v WHERE v.dataProximaDose BETWEEN :inicio AND :fim")
    List<Vacinacao> findProximasVacinas(
        @Param("inicio") LocalDate inicio, 
        @Param("fim") LocalDate fim);
    
    @Query("SELECT v FROM Vacinacao v WHERE v.animal.id = :animalId")
    List<Vacinacao> findByAnimalId(@Param("animalId") Long animalId);
    
    @Query("SELECT v FROM Vacinacao v WHERE v.veterinario.id = :veterinarioId")
    List<Vacinacao> findByVeterinarioId(@Param("veterinarioId") Long veterinarioId);
}