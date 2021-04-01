package matyliano.exception.handler;

import matyliano.exception.exist.ClientAlreadyExistException;
import matyliano.exception.exist.TaskAlreadyExistException;
import matyliano.exception.exist.UserAlreadyExistException;
import matyliano.exception.notFound.ClientNotFoundException;
import matyliano.exception.notFound.NotEmptyException;
import matyliano.exception.notFound.TaskNotFoundException;
import matyliano.exception.notFound.TokenException;
import matyliano.exception.notFound.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class Handler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleRuntimeException(UserNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleRuntimeException(ClientNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleRuntimeException(TaskNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(ClientAlreadyExistException.class)
    public ResponseEntity<Object> handleRuntimeException(ClientAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FOUND);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleRuntimeException(UserAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FOUND);
    }
    @ExceptionHandler(TaskAlreadyExistException.class)
    public ResponseEntity<Object> handleRuntimeException(TaskAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FOUND);
    }
    @ExceptionHandler(NotEmptyException.class)
    public ResponseEntity<Object> notEmptyHandleRuntimeException(NotEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Object> tokenHandleRuntimeException(NotEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
