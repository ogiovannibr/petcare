package com.example.demo.repository;

import com.example.demo.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    @Query("SELECT t FROM Tutor t WHERE t.cpf = :cpf")
    Optional<Tutor> findByCpf(@Param("cpf") String cpf);
}