package br.com.pinheirostec.gerenciadorrifaonline.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ResponseException> handleNegocioException(NegocioException exception) {
        ResponseException res = new ResponseException(LocalDateTime.now(), exception.getMsg(), HttpStatus.UNPROCESSABLE_ENTITY.toString(), null);
        return ResponseEntity.unprocessableEntity().body(res);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseException> handleNegocioException(NotFoundException exception) {
        ResponseException res = new ResponseException(LocalDateTime.now(), exception.getMsg(), HttpStatus.NOT_FOUND.toString(), null);
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseException> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception) {
        List<DetailsResponseException> errors = new ArrayList<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(new DetailsResponseException(error.getField(), error.getDefaultMessage()));
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.add(new DetailsResponseException(error.getObjectName(), error.getDefaultMessage()));
        }
        ResponseException res = new ResponseException(LocalDateTime.now(), "Erro na validacao dos campos", HttpStatus.BAD_REQUEST.toString(), errors);
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handleGenericException(Exception exception) {
        ResponseException res = new ResponseException(LocalDateTime.now(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), null);
        return ResponseEntity.internalServerError().body(res);
    }

}
