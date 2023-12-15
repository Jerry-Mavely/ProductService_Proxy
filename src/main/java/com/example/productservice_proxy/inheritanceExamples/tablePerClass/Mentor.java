package com.example.productservice_proxy.inheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "TPC_Mentor")
public class Mentor extends User{
    private int gradYear;
}
