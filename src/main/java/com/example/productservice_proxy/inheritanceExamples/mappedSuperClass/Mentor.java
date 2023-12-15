package com.example.productservice_proxy.inheritanceExamples.mappedSuperClass;

import com.example.productservice_proxy.inheritanceExamples.tablePerClass.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "mps_Mentor")
public class Mentor extends User {
    private int gradYear;
}
