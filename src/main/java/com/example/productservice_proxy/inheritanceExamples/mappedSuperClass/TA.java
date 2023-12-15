package com.example.productservice_proxy.inheritanceExamples.mappedSuperClass;

import com.example.productservice_proxy.inheritanceExamples.tablePerClass.User;
import jakarta.persistence.Entity;

@Entity(name="mps_TA")
public class TA extends User {
    private double rating;
}
