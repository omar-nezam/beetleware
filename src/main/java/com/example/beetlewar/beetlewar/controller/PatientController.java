package com.example.beetlewar.beetlewar.controller;


import com.example.beetlewar.beetlewar.dto.PatientDto;
import com.example.beetlewar.beetlewar.model.Patient;
import com.example.beetlewar.beetlewar.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {
  private final PatientService patientService;

  @GetMapping("/get/All")
  public ResponseEntity<List<Patient>> getAllPatient() {
        return new ResponseEntity<>(patientService.getAllPAtient(), HttpStatus.OK);
    }
    @GetMapping("/get/AllByDate/{date}")
    public ResponseEntity<List<Patient>> getAllPatientByDate(@PathVariable Date date) {
        return new ResponseEntity<>(patientService.getAllPAtientbyDate(date), HttpStatus.OK);
    }
  @GetMapping("/get/{name}")
  public  ResponseEntity<List<Patient>> getPatientByName(@PathVariable String name){
      return new ResponseEntity<>(patientService.getPatientByName(name), HttpStatus.OK);
  }

@PutMapping("/add")
public ResponseEntity<String> addPatient(PatientDto patientDto){
      return new ResponseEntity<>(patientService.addPatient(patientDto),HttpStatus.OK);
}
@PostMapping("/update/{id}")
public ResponseEntity<String> updatePatient (@RequestBody PatientDto patientDto,@PathVariable int id){
     return new ResponseEntity<> (patientService.updatePatient(patientDto,id),HttpStatus.OK);
}
@DeleteMapping("/delete")
public ResponseEntity<String> deletePatient(@RequestBody PatientDto patientDto){
      return new ResponseEntity<>(patientService.deletePatient(patientDto),HttpStatus.OK);
}


}
