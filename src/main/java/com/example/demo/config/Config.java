package com.example.demo.config;

import com.example.demo.mapper.ClientMapper;
import com.example.demo.mapper.ManagerMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class Config {

    @Bean
    public SqlSession session() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    @Bean
    public ManagerMapper managerMapper() throws IOException {
        return session().getMapper(ManagerMapper.class);
    }

    @Bean
    public ClientMapper clientMapper() throws IOException {
        return session().getMapper(ClientMapper.class);
    }
}
