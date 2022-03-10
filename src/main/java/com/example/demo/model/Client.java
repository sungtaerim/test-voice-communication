package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder(toBuilder = true)
public class Client {

    private int clientId;
    private String name;
    private String address;
    private Manager manager;
    private boolean isDeleted;
}
