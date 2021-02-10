package matyliano.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message, Long id) {
        super(message + "with id: " + id + " not found");
    }
    public ClientNotFoundException(String message) {
        super(message);
    }
}
