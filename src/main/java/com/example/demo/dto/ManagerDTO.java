package com.example.demo.dto;

import com.example.demo.model.Manager;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private int telephone;
    private ManagerDTO assistant;
    private List<ClientDTO> clientDTOList;

    public ManagerDTO(Manager manager) {
        id = manager.getManagerId();
        firstName = manager.getFirstName();
        lastName = manager.getLastName();
        middleName = manager.getMiddleName();
        telephone = manager.getTelephone();
        assistant = manager.getAlternate() != null ? new ManagerDTO(manager.getAlternate()) : null;
        clientDTOList = !manager.getClients().isEmpty() ? manager.getClients().stream().map(ClientDTO::new).collect(Collectors.toList()) : null;
    }

    public Manager dtoToObject() {
        Manager manager = new Manager();
        manager.setManagerId(this.id);
        manager.setFirstName(this.firstName);
        manager.setLastName(this.lastName);
        manager.setMiddleName(this.middleName);
        manager.setTelephone(this.telephone);
        manager.setAlternate(this.assistant != null ? this.assistant.dtoToObject() : null);

        return manager;
    }
}
