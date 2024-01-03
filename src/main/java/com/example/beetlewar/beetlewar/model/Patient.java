package com.example.beetlewar.beetlewar.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity
public class Patient {
 @Id
 @GeneratedValue(strategy = IDENTITY)
private Integer id;
private String name;
private Date date;
private String status;
private  String reason;


@Enumerated(EnumType.STRING)
private Role role ;
}
