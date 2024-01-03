package com.example.beetlewar.beetlewar.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PatientDto {
private String name;
private Date date;
private  String status;
private String reason ;



}
