package com.example.beetlewar.beetlewar.repository;

import com.example.beetlewar.beetlewar.model.Patient;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findByName(String name);

    List<Patient> findPatientsBydate(Date date);
    boolean existsByName(String name);
}
