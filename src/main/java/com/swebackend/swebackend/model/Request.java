package com.swebackend.swebackend.model;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity @Data
public class Request {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aplicantName;
    private Double amount;  
    private RequestState state;
    private String nif;
    private String currency;
    private Date creationDate;
}  
