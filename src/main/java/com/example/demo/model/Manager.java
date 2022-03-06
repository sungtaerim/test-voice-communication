package com.example.demo.model;

import lombok.*;

import java.util.List;

@Data
//@Builder
public class Manager {

    private int managerId;
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer telephone;
    private Manager alternate;

    private List<Client> clients;
}
