package com.example.demo.mapper;

import com.example.demo.model.Client;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientMapperTest {

    @Autowired
    ClientMapper clientMapper;
    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    SqlSession session;

    @Test
    void insertClient() {
        Client client = new Client();
        client.setClientId(100);
        client.setName("test");
        client.setAddress("test");
        client.setManager(managerMapper.getManagerById(1));

        clientMapper.insertClient(client);
        session.commit();
        assertNotNull(clientMapper.getClientById(100));
    }

    @Test
    void getClientById() {
        Client client = clientMapper.getClientById(100);
        assertEquals(client.getClientId(), 100);
    }

    @Test
    void getClients() {
        List<Client> clients = clientMapper.getClients();
        assertNotEquals(clients.size(), 0);
    }

    @Test
    void updateClient() {
        Client client = clientMapper.getClientById(100);
        client.setName("new name");
        clientMapper.updateClient(client);
        session.commit();
        client = clientMapper.getClientById(100);
        assertEquals(client.getName(), "new name");
    }

    @Test
    void deleteClient() {
        clientMapper.deleteClient(100);
        session.commit();
        Client client = clientMapper.getClientById(100);
        assertNull(client);
    }
}