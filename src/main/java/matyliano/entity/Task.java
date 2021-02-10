package matyliano.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matyliano.enums.TaskStatus;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    private String creator;

    @ManyToMany(mappedBy = "taskList")
    private List<User> assignedEmployees;

    private String taskDescription;

    private String note;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @CreatedDate
    private LocalDate creationDate;

    @Timestamp
    private LocalDate modificationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "client_task",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    @JsonIgnore
    private List<Client> clients;

    public void assignClientToTask(Client existingClient) {
        this.getClients().add(existingClient);
    }
}
