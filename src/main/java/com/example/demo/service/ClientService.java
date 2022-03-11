package com.example.demo.service;

import com.example.demo.dao.ClientDAO;
import com.example.demo.dto.ClientDTO;
import com.example.demo.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientService {

    public static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private ClientDAO clientDAO;

    @Autowired
    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public ClientDTO getById(Integer id) throws Exception {
        Client client = clientDAO.getClientById(id);
        if (client != null) {
            return new ClientDTO(client);
        }
        throw new Exception("This client not exist");
    }

    public List<ClientDTO> getByManager(Integer id) throws Exception {
        List<Client> clients = clientDAO.getClients();
        if (clients.isEmpty()) {
            throw new Exception("Clients not found");
        }
        return clients.stream().map(client -> new ClientDTO(client, 1)).collect(Collectors.toList());
    }

    public List<ClientDTO> getList() throws Exception {
        List<Client> clients = clientDAO.getClients();
        if (clients.isEmpty()) {
            throw new Exception("Clients not found");
        }
        return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public void insert(ClientDTO clientDTO) {
        try {
            Client client = clientDTO.dtoToObject();
            clientDAO.insert(client);

            logger.info("Client " + clientDTO.getId() + " successfully added");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

    public void update(ClientDTO clientDTO) {
        try {
            Client client = this.getById(clientDTO.getId()).dtoToObject();
            client.setAddress(clientDTO.getAddress() != null ? clientDTO.getAddress() : client.getAddress());
            client.setName(clientDTO.getName() != null ? clientDTO.getName() : client.getName());
            clientDAO.update(client);

            logger.info("Client " + clientDTO.getId() + " successfully updated");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

    public void delete(Integer id) {
        try {
            clientDAO.delete(id);

            logger.info("Client " + id + " successfully deleted");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
}
