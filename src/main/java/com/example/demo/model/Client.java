package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {

    private int clientId;
    private String name;
    private String address;
    private Manager manager;
    private boolean isDeleted;
}
