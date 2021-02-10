package matyliano.controller;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import matyliano.dto.ClientDTO;
import matyliano.entity.Client;
import matyliano.entity.Task;
import matyliano.entity.User;
import matyliano.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/new")
    public ResponseEntity<Client> addNewClient(@RequestBody ClientDTO newClient, User user) {
        log.info("Request to create client: {}", newClient);
        return ResponseEntity.ok().body(clientService.addNewClient(newClient, user));
    }
    
    @GetMapping("/findById/{id}")
    ResponseEntity<Optional<Client>> getClient(@PathVariable Long id) {
        log.info("Request to get client : {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody ClientDTO client, @PathVariable Long id) {
        log.info("Request to update client: {}", client);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(id, client));
    }

    @PostMapping("/assignTask/{taskId}/assignClient/{clientId}")
    public ResponseEntity<Task> assignClientToTask(@PathVariable Long taskId, @PathVariable Long clientId) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.assignClientToTask(taskId,clientId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.info("Request to delete client with id : {}", id);
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
