package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
public class Manager {

    private int managerId;
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer telephone;
    private Manager alternate;

    private List<Client> clients;
}
