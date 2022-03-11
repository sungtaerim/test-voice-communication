package com.example.demo.service;

import com.example.demo.dao.ClientDAO;
import com.example.demo.dao.ManagerDAO;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ManagerDTO;
import com.example.demo.model.Client;
import com.example.demo.model.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ManagerService {

    public static final Logger logger = LoggerFactory.getLogger(ManagerService.class);
    public ClientDAO clientDAO;
    public ManagerDAO managerDAO;

    @Autowired
    public ManagerService(ClientDAO clientDAO, ManagerDAO managerDAO) {
        this.clientDAO = clientDAO;
        this.managerDAO = managerDAO;
    }

    public ManagerDTO getById(Integer id) throws Exception {
        Manager manager = managerDAO.getManagerById(id);
        if (manager != null) {
            manager.setClients(clientDAO.getClientByManager(manager.getManagerId()));
            return new ManagerDTO(manager);
        }
        throw new Exception("This client not exist");
    }

    public List<ManagerDTO> getList() throws Exception {
        List<Manager> managers = managerDAO.getManagers();
        if (managers.isEmpty()) {
            throw new Exception("Managers not found");
        }
        return managers.stream().map(ManagerDTO::new).collect(Collectors.toList());
    }
}
