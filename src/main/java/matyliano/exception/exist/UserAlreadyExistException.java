package matyliano.exception.exist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message, Long id) {
        super(message + "with id: " + id + " already exist");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
