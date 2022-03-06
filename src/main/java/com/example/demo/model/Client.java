package com.example.demo.model;

import lombok.Data;

@Data
public class Client {

    private int clientId;
    private String name;
    private String address;
    private Manager manager;
    private boolean isDeleted;
}
