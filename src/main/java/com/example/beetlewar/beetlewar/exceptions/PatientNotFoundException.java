package com.example.beetlewar.beetlewar.exceptions;

public class PatientNotFoundException extends RuntimeException{
    private static final long serialVersionUTD=1;

    public PatientNotFoundException(String message){
        super(message);
    }
}
