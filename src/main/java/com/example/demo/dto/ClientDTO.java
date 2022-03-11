package com.example.demo.dto;

import com.example.demo.model.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    private int id;
    private String name;
    private String address;
    private ManagerDTO manager;
    private boolean isDeleted;

    public ClientDTO(Client client) {
        id = client.getClientId();
        name = client.getName();
        address = client.getAddress();
        manager = new ManagerDTO(client.getManager());
        isDeleted = client.isDeleted();
    }

    public ClientDTO(Client client, int i) {
        id = client.getClientId();
        name = client.getName();
        address = client.getAddress();
        isDeleted = client.isDeleted();
    }

    public Client dtoToObject() {
        Client client = new Client();
        client.setClientId(this.id);
        client.setName(this.name);
        client.setAddress(this.address);
        client.setManager(this.manager.dtoToObject());
        client.setDeleted(this.isDeleted);

        return client;
    }
}
