package matyliano.dto;

import java.time.LocalDate;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matyliano.entity.Client;
import matyliano.entity.User;
import matyliano.enums.TaskStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;

    private User assignedEmployee;

    private String taskDescription;

    private String note;

    private TaskStatus taskStatus;

    @Timestamp
    private LocalDate modificationDate;

    private Client client;

    private User creator;

}
