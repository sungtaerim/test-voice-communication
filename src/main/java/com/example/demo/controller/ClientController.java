package com.example.demo.controller;

import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/client"})
public class ClientController extends ModelController {

    private ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    /**
     * GET request to get the client entity by id. If id is null, the list of client entities is returned
     * @param id RequestParam client's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @GetMapping({"/get"})
    public ResponseEntity<?> getClient(@RequestParam(required = false) Integer id) {
        try {
            if (id != null) {
                Client client = clientMapper.getClientById(id);
                return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);
            }

            return new ResponseEntity<>(clientMapper.getClients(), new HttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * POST request to add a new Client entity to the database
     * @param client Client entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/insert"})
    public ResponseEntity<?> insertClient(@RequestBody Client client) {
        try {
            clientMapper.insertClient(client);
            session.commit();

            logger.error("Client " + client.getClientId() + " successfully added");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    /**
     * POST request to to update a Client entity to the database
     * @param client Client entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/update"})
    public ResponseEntity<?> updateCLinet(@RequestBody Client client) {
        try {
            Client updateClient = clientMapper.getClientById(client.getClientId());
            if (client.getAddress() != null) {
                updateClient.setAddress(client.getAddress());
            }
            if (client.getName() != null) {
                updateClient.setName(client.getName());
            }
            clientMapper.updateClient(updateClient);
            session.commit();

            logger.error("Client " + client.getClientId() + " successfully updated");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * DELETE request to remove a Client entity from the database by client id
     * @param id RequestParam client's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @DeleteMapping({"/delete"})
    public ResponseEntity<?> deleteClient(@RequestParam Integer id) {
        try {
            clientMapper.deleteClient(id);
            session.commit();

            logger.error("Client " + id + " successfully deleted");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}
