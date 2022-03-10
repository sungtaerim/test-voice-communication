package com.example.demo.controller;

import com.example.demo.dao.ClientDAO;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/client"})
public class ClientController {

    private ClientDAO clientDAO;

    @Autowired
    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * GET request to get the client entity by id. If id is null, the list of client entities is returned
     * @param id PathVariable client's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @GetMapping({"/get/{id}"})
    public ResponseEntity<?> getClientById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(clientDAO.getClientById(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET request to get the list of client entities
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @GetMapping({"/get"})
    public ResponseEntity<?> getClients() throws Exception {
        return new ResponseEntity<>(clientDAO.getClients(), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * POST request to add a new Client entity to the database
     * @param client Client entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/insert"})
    public ResponseEntity<?> insertClient(@RequestBody Client client) {
        return clientDAO.insert(client);
    }

    /**
     * POST request to to update a Client entity to the database
     * @param client Client entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/update"})
    public ResponseEntity<?> updateCLinet(@RequestBody Client client) {
        return clientDAO.update(client);
    }

    /**
     * DELETE request to remove a Client entity from the database by client id
     * @param id RequestParam client's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @DeleteMapping({"/delete"})
    public ResponseEntity<?> deleteClient(@RequestParam Integer id) {
        return clientDAO.delete(id);
    }
}
