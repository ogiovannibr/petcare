package com.example.demo.repository;

import com.example.demo.model.Animal;
import com.example.demo.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a WHERE a.tutor = :tutor")
    List<Animal> findByTutor(@Param("tutor") Tutor tutor);
    
    @Query("SELECT a FROM Animal a WHERE a.especie = :especie")
    List<Animal> findByEspecie(@Param("especie") String especie);
    
    @Query("SELECT a FROM Animal a WHERE a.tutor.id = :tutorId")
    List<Animal> findByTutorId(@Param("tutorId") Long tutorId);
}