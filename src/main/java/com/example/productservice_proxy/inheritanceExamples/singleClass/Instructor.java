package com.example.productservice_proxy.inheritanceExamples.singleClass;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "SC_Instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String company;
}
