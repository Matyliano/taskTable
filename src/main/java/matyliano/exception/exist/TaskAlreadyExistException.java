package matyliano.exception.exist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class TaskAlreadyExistException extends RuntimeException {

    public TaskAlreadyExistException(String message, Long id) {
        super(message + "with id: " + id + " already exist");
    }

    public TaskAlreadyExistException(String message) {
        super(message + " already exist");
    }
}
