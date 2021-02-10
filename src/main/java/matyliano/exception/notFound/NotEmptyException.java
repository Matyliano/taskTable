package matyliano.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEmptyException extends RuntimeException {
    public NotEmptyException(String message) {
        super(message + "fields cannot be empty");
    }
}
