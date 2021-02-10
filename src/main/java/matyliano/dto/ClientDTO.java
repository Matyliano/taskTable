package matyliano.dto;

import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matyliano.entity.Task;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    private String clientName;

    @CreatedDate
    private LocalDate creationDateTime;

    private String description;

    private String notes;

    private Set<Task> tasks;

    @CreatedBy
    private String addingUser;
}
