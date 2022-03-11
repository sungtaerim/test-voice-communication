package com.example.demo.dao;

import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAO {

    private final ClientMapper clientMapper;
    private final SqlSession session;

    @Autowired
    public ClientDAO(SqlSession session) {
        this.session = session;
        clientMapper = session.getMapper(ClientMapper.class);
    }

    public Client getClientById(Integer id) {
        return clientMapper.getClientById(id);
    }

    public List<Client> getClientByManager(Integer id) {
        return clientMapper.getClientByManager(id);
    }


    public List<Client> getClients() {
        return clientMapper.getClients();
    }

    public void insert(Client client) {
        clientMapper.insertClient(client);
        session.commit();
    }

    public void update(Client client) {
        clientMapper.updateClient(client);
        session.commit();
    }

    public void delete(Integer id) {
        clientMapper.deleteClient(id);
        session.commit();
    }
}
