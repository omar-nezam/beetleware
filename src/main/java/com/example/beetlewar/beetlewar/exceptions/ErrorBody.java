package com.example.beetlewar.beetlewar.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorBody {
    private Integer statusCode;
    private String message;
    private Date timeStamp;
}
