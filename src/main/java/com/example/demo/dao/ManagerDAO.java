package com.example.demo.dao;

import com.example.demo.dto.ManagerDTO;
import com.example.demo.mapper.ManagerMapper;
import com.example.demo.model.Manager;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerDAO {

    private final SqlSession session;
    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerDAO(SqlSession session) {
        this.session = session;
        managerMapper = session.getMapper(ManagerMapper.class);
    }

    public Manager getManagerById(Integer id) {
        return managerMapper.getManagerById(id);
    }

    public List<Manager> getManagers() {
        return managerMapper.getManagers();
    }

    public void insert(Manager manager) {
        managerMapper.insertManager(manager);
        session.commit();
    }

    public void update(Manager manager) {
        managerMapper.updateManager(manager);
        session.commit();
    }

    public void delete(Integer id) {
        managerMapper.deleteManager(id);
        session.commit();
    }
}
