package com.example.beetlewar.beetlewar.repository;

import com.example.beetlewar.beetlewar.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUserName(String username);
    boolean  existsByUserName(String username);



}
