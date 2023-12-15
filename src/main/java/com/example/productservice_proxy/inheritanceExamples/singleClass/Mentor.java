package com.example.productservice_proxy.inheritanceExamples.singleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "SC_Mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private int gradYear;
}
