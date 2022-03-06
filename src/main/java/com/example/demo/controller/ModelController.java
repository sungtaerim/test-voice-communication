package com.example.demo.controller;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

public abstract class ModelController {

    @Autowired
    public SqlSession session;

    public static final Logger logger = LoggerFactory.getLogger(ModelController.class);

    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json");

        return httpHeaders;
    }
}
