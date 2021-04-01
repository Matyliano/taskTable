package matyliano.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenException extends RuntimeException {

    public TokenException(String message) {
        super(message);
    }
}
