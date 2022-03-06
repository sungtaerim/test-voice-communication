package com.example.demo.controller;

import com.example.demo.mapper.ClientMapper;
import com.example.demo.mapper.ManagerMapper;
import com.example.demo.model.Client;
import com.example.demo.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/manager"})
public class ManagerController extends ModelController {

    private ManagerMapper managerMapper;
    private ClientMapper clientMapper;

    @Autowired
    public ManagerController(ManagerMapper managerMapper, ClientMapper clientMapper) {
        this.managerMapper = managerMapper;
        this.clientMapper = clientMapper;
    }

    /**
     * GET request to get the manager entity by id. If id is null, the list of manager entities is returned
     * @param id RequestParam manager's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @GetMapping({"/get"})
    public ResponseEntity<?> getManager(@RequestParam(required = false) Integer id) {
        try {
            if (id != null) {
                Manager manager = managerMapper.getManagerById(id);
                return new ResponseEntity<>(manager, new HttpHeaders(), HttpStatus.OK);
            }
            return new ResponseEntity<>(managerMapper.getManagers(), getHttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * POST request to add a new Manager entity to the database
     * @param manager Manager entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/insert"})
    public ResponseEntity<?> insertManager(@RequestBody Manager manager) {
        try {
            managerMapper.insertManager(manager);
            session.commit();

            logger.error("Manager " + manager.getManagerId() + " successfully added");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * POST request to to update a Manager entity to the database
     * @param manager Manager entity
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @PostMapping({"/update"})
    public ResponseEntity<?> updateCLinet(@RequestBody Manager manager) {
        try {
            Manager updateManager = managerMapper.getManagerById(manager.getManagerId());

            List<Method> methodsGet = Arrays.stream(updateManager.getClass().getDeclaredMethods())
                    .filter(method -> method.getName().startsWith("get"))
                    .filter(method -> {
                        try {
                            return method.invoke(manager) != null;
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        return false;
                    }).collect(Collectors.toList());

            List<Method> methodsSet = Arrays.stream(updateManager.getClass().getDeclaredMethods())
                    .filter(method -> method.getName().startsWith("set"))
                    .collect(Collectors.toList());

            for (Method methodGet : methodsGet) {
                for (Method methodSet : methodsSet) {
                    if (methodSet.getName().substring(3).equals(methodGet.getName().substring(3))) {
                        methodSet.invoke(updateManager, methodGet.invoke(manager));
                        break;
                    }
                }
            }

            managerMapper.updateManager(updateManager);
            session.commit();

            logger.error("Manager " + updateManager.getManagerId() + " successfully updated");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * DELETE request to remove a customer entity from the database.
     * If the current manager was a deputy, it will be null.
     * For clients whose manager was the current manager, the deputy will be alternate manager.
     * If no deputy is assigned, the deletion is not possible.
     * @param id RequestParam manager's id
     * @return Returns ResponseEntity with code 200 if successful, 404 otherwise
     * */
    @DeleteMapping({"/delete"})
    public ResponseEntity<?> deleteManager(@RequestParam Integer id) {
        try {
            Manager managerDelete = managerMapper.getManagerById(id);
            if (managerDelete.getAlternate() == null) {
                return new ResponseEntity<>("Unable to delete manager without alternate manager", new HttpHeaders(), HttpStatus.FORBIDDEN);
            }

            List<Client> clients = clientMapper.getClientByManager(managerDelete.getManagerId());
            clients.forEach(client -> clientMapper.updateClientManager(managerDelete, client.getClientId()));

            List<Manager> managers = managerMapper.getManagersByAlternate(managerDelete.getManagerId());

            managers.forEach(manager -> {
                manager.setAlternate(null);
                managerMapper.updateManager(manager);
                session.commit();
            });

            managerMapper.deleteManager(managerDelete.getManagerId());
            session.commit();

            logger.error("Manager " + id + " successfully deleted");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}
