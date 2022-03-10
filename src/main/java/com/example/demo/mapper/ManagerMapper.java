package com.example.demo.mapper;

import com.example.demo.model.Client;
import com.example.demo.model.Manager;

import java.util.List;

public interface ManagerMapper {

    Manager getManagerById(Integer id);

    List<Manager> getManagers();

    List<Manager> getManagersByAlternate(Integer id);

    List<Client> getClients(Integer id);

    void insertManager(Manager manager);

    void updateManager(Manager manager);

    void deleteManager(Integer id);
}
