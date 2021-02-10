package matyliano.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
    
    public TaskNotFoundException(String message, Long id) {
        super(message + "with id: " + id + " not found");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
