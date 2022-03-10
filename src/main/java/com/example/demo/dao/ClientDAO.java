package com.example.demo.dao;

import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAO {

    public static final Logger logger = LoggerFactory.getLogger(ClientDAO.class);
    private final ClientMapper clientMapper;
    private final SqlSession session;

    @Autowired
    public ClientDAO(ClientMapper clientMapper, SqlSession session) {
        this.clientMapper = clientMapper;
        this.session = session;
    }

    public Client getClientById(Integer id) throws Exception {
        Client client = clientMapper.getClientById(id);
        if (client == null) {
            logger.error("Client not found");
            throw new Exception("Client not found");
        }
        return client;
    }

    public List<Client> getClients() throws Exception {
        List<Client> clientList = clientMapper.getClients();
        if (clientList.isEmpty()) {
            logger.error("Clients not found");
            throw new Exception("Clients not found");
        }
        return clientList;
    }

    public ResponseEntity<?> insert(Client client) {
        try {
            clientMapper.insertClient(client);
            session.commit();

            logger.info("Client " + client.getClientId() + " successfully added");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> update(Client client) {
        try {
            Client clientUpdate = this.getClientById(client.getClientId());
            clientUpdate.setAddress(client.getAddress() != null ? client.getAddress() : clientUpdate.getAddress());
            clientUpdate.setName(client.getName() != null ? client.getName() : clientUpdate.getName());
            clientMapper.updateClient(clientUpdate);
            session.commit();

            logger.info("Client " + client.getClientId() + " successfully updated");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            clientMapper.deleteClient(id);
            session.commit();

            logger.info("Client " + id + " successfully deleted");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.status(500).build();
    }
}
