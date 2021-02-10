package matyliano.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "author_id")
    @ManyToOne(cascade = {CascadeType.ALL})
    private User author;

    @JoinColumn(name = "task_id")
    @ManyToOne(cascade = {CascadeType.ALL})
    private Task taskReceiver;

    private String note;

    @CreatedDate
    private LocalDate whenNoteWasAdded;
}
