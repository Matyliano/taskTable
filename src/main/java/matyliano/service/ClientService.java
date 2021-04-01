package matyliano.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import matyliano.dto.ClientDTO;
import matyliano.entity.Client;
import matyliano.entity.Task;
import matyliano.entity.User;
import matyliano.exception.notFound.ClientNotFoundException;
import matyliano.repository.ClientRepository;
import matyliano.repository.TaskRepository;
import matyliano.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    private Client mapInputToClient(ClientDTO client, User user) {
        String loggedUserName = userRepository.findFullNameByUsername(user.getUsername())
                .orElseGet(user::getUsername);

        return Client.builder()
                .addingUser(loggedUserName)
                .creationDateTime(LocalDate.now())
                .clientName(client.getClientName())
                .description(client.getDescription())
                .notes(client.getNotes())
                .build();
    }

    public Client addNewClient(ClientDTO newClient, User user) {
        return clientRepository.save(mapInputToClient(newClient, user));
    }

    public Client updateClient(Long id, ClientDTO updateClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " does not exist"));

        if (updateClient.getClientName() != null) {
            client.setClientName(updateClient.getClientName());
        }
        if (updateClient.getNotes() != null) {
            client.setNotes(updateClient.getNotes());
        }
        if (updateClient.getDescription() != null) {
            client.setDescription(updateClient.getDescription());
        }
        if (updateClient.getAddingUser() != null) {
            client.setAddingUser(updateClient.getAddingUser());
        }

        return clientRepository.save(client);
    }

    public Task assignClientToTask(Long taskId, Long clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client with id" + clientId + "does not exist"));

        Task existingTask = taskService.getOneTask(taskId);

        existingTask.assignClientToTask(existingClient);
        return taskRepository.saveAndFlush(existingTask);
    }
}
