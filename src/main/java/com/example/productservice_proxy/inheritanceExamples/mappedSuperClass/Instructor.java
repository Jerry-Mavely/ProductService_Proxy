package com.example.productservice_proxy.inheritanceExamples.mappedSuperClass;


import com.example.productservice_proxy.inheritanceExamples.tablePerClass.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "mps_Instructor")
public class Instructor extends User {
    private String company;
}
