package com.example.demo.mapper;

import com.example.demo.model.Client;
import com.example.demo.model.Manager;

import java.util.List;

public interface ClientMapper {

    Client getClientById(Integer id);

    List<Client> getClients();

    List<Client> getClientByManager(Integer managerId);

    void insertClient(Client client);

    void updateClient(Client client);

    void updateClientManager(Manager manager, Integer id);

    void deleteClient(Integer id);
}
