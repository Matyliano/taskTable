package matyliano.exception.exist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ClientAlreadyExistException extends RuntimeException {

    public ClientAlreadyExistException(String message, Long id) {
        super(message + "with id: " + id + " already exist");
    }

    public ClientAlreadyExistException(String message) {
        super(message + " already exist");
    }
}
