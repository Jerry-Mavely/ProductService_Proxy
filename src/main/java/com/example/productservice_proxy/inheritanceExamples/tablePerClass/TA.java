package com.example.productservice_proxy.inheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="TPC_TA")
public class TA extends User{
    private double rating;
}
