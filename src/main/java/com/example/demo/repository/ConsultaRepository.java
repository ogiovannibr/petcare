package com.example.demo.repository;

import com.example.demo.model.Consulta;
import com.example.demo.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("SELECT c FROM Consulta c WHERE c.veterinario = :veterinario AND c.dataHora BETWEEN :inicio AND :fim AND c.status = 'AGENDADA'")
    List<Consulta> findByVeterinarioAndDataHoraBetween(
        @Param("veterinario") Veterinario veterinario, 
        @Param("inicio") LocalDateTime inicio, 
        @Param("fim") LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.status = 'AGENDADA' ORDER BY c.dataHora")
    List<Consulta> findProximasConsultas();
    
    @Query("SELECT c FROM Consulta c WHERE c.animal.id = :animalId")
    List<Consulta> findByAnimalId(@Param("animalId") Long animalId);
    
    @Query("SELECT c FROM Consulta c WHERE c.veterinario.id = :veterinarioId")
    List<Consulta> findByVeterinarioId(@Param("veterinarioId") Long veterinarioId);
}