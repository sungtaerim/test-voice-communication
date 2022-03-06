package com.example.demo.mapper;

import com.example.demo.model.Manager;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ManagerMapperTest {

    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    SqlSession session;

    @Test
    void getManagerById() {
        Manager manager = managerMapper.getManagerById(1);
        assertEquals(manager.getManagerId(), 1);
    }

    @Test
    void getManagers() {
        List<Manager> managerList = managerMapper.getManagers();
        assertNotEquals(managerList.size(), 0);
    }

    @Test
    void insertManager() {
        Manager manager = new Manager();
        manager.setManagerId(100);
        manager.setFirstName("test");
        manager.setLastName("test");
        manager.setMiddleName("test");
        manager.setTelephone(123456);
        manager.setAlternate(managerMapper.getManagerById(1));

        managerMapper.insertManager(manager);
        session.commit();
        assertNotNull(managerMapper.getManagerById(100));
    }

    @Test
    void getManagersByAlternate() {
        Manager manager = managerMapper.getManagersByAlternate(1).get(0);
        assertEquals(manager.getManagerId(), 100);
    }

    @Test
    void updateManager() {
        Manager manager = managerMapper.getManagerById(100);
        manager.setAlternate(null);
        managerMapper.updateManager(manager);
        session.commit();
        manager = managerMapper.getManagerById(100);
        assertNull(manager.getAlternate());
    }

    @Test
    void deleteManager() {
        managerMapper.deleteManager(100);
        session.commit();
        Manager manager = managerMapper.getManagerById(100);
        assertNull(manager);
    }
}