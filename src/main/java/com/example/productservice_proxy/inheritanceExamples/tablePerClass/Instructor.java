package com.example.productservice_proxy.inheritanceExamples.tablePerClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "TPC_Instructor")
public class Instructor extends User{
    private String company;
}
