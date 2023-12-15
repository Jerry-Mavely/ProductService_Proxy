package com.example.productservice_proxy.inheritanceExamples.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jt_TA")
@PrimaryKeyJoinColumn(name="user_id")
public class TA extends User {
    private double rating;
}
