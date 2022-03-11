package com.example.demo.controller;

import com.example.demo.dto.ClientDTO;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/client"})
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * GET request to get the client entity by id. If id is null, the list of client entities is returned
     * @param id PathVariable client's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @GetMapping({"/get/{id}"})
    public ResponseEntity<?> getClientById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(clientService.getById(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET request to get the list of client entities
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @GetMapping({"/get"})
    public ResponseEntity<?> getClients() throws Exception {
        return new ResponseEntity<>(clientService.getList(), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * POST request to add a new Client entity to the database
     * @param clientDTO Client entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/insert"})
    public ResponseEntity<?> insertClient(@RequestBody ClientDTO clientDTO) {
        clientService.insert(clientDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * POST request to to update a Client entity to the database
     * @param clientDTO Client entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/update"})
    public ResponseEntity<?> updateCLinet(@RequestBody ClientDTO clientDTO) {
        clientService.update(clientDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE request to remove a Client entity from the database by client id
     * @param id RequestParam client's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @DeleteMapping({"/delete"})
    public ResponseEntity<?> deleteClient(@RequestParam Integer id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
