package com.example.beetlewar.beetlewar.service;


import com.example.beetlewar.beetlewar.dto.PatientDto;
import com.example.beetlewar.beetlewar.model.Patient;

import java.util.Date;
import java.util.List;

public interface PatientService {
    List<Patient> getAllPAtient ();
    List<Patient> getAllPAtientbyDate (Date date);
    List<Patient> getPatientByName (String name);

    String addPatient(PatientDto patientDto);
    String updatePatient(PatientDto patientDto , int id);

    String deletePatient( PatientDto sellerDto);
}
