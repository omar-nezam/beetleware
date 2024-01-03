package com.example.beetlewar.beetlewar.service.impl;

import com.example.beetlewar.beetlewar.dto.PatientDto;
import com.example.beetlewar.beetlewar.exceptions.PatientNotFoundException;
import com.example.beetlewar.beetlewar.model.Patient;
import com.example.beetlewar.beetlewar.model.Role;
import com.example.beetlewar.beetlewar.repository.PatientRepository;
import com.example.beetlewar.beetlewar.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
   private final PatientRepository patientRepository;
    @Override
    public List<Patient> getAllPAtient() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getAllPAtientbyDate(Date date) {
        return patientRepository.findPatientsBydate(date);
    }

    @Override
    public List<Patient> getPatientByName(String name) {
        return patientRepository.findByName(name);
    }


    @Override
    public String addPatient(PatientDto patientDto) {
        Patient patient= Patient.builder()
                .name(patientDto.getName())
                .date(patientDto.getDate())
                .status("Attend")
                .role(Role.PATIENT)
                .build();
        patientRepository.save(patient);
        return "new patient with name "+ patient.getName()+" added successfully";
    }

    @Override
    public String updatePatient(PatientDto patientDto, int id) {
        Patient patient=patientRepository.findById(id).
                orElseThrow(()->new PatientNotFoundException("there is no patient with this id "));
        patient.setName(patientDto.getName());
        patient.setDate(patientDto.getDate());
        if(!patientDto.getStatus().equalsIgnoreCase("Attend")){
            patient.setStatus("Cancelled");
            if(patientDto.getReason()==null) {
                patient.setReason("Cancelled");
            }else {
                patient.setReason(patientDto.getReason());
            }
        }
        patientRepository.save(patient);
        return "patient updated to "+patientDto.getName();
    }

    @Override
    public String deletePatient(PatientDto patientDto) {
        List<Patient> patient=patientRepository.findByName(patientDto.getName());
        if (patient==null){throw new PatientNotFoundException(patientDto.getName());}
        patientRepository.deleteAll(patient);
        return "patient "+patientDto.getName()+" deleted ";
    }
}
