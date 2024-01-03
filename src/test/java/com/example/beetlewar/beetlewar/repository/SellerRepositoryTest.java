package com.example.beetlewar.beetlewar.repository;


import com.example.beetlewar.beetlewar.model.Patient;
import com.example.beetlewar.beetlewar.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SellerRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void tryToFindSellerByName() {
      Patient patient= Patient.builder().name("momo").role(Role.PATIENT).build();
        patientRepository.save(patient);
      assertThat(patientRepository.findByName(patient.getName())).isEqualTo(patient);
    }

    @Test
    void checkExistsOfSellerByName() {
        Patient patient= Patient.builder().name("momo").role(Role.PATIENT).build();
        patientRepository.save(patient);
        assertThat(patientRepository.existsByName(patient.getName())).isEqualTo(true);
    }
}