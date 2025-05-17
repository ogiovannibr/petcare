package com.example.demo.repository;

import com.example.demo.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    @Query("SELECT v FROM Veterinario v WHERE :especializacao MEMBER OF v.especializacoes")
    List<Veterinario> findByEspecializacao(@Param("especializacao") String especializacao);
    
    @Query("SELECT v FROM Veterinario v WHERE v.crmv = :crmv")
    Optional<Veterinario> findByCrmv(@Param("crmv") String crmv);
}