package br.com.zup.ecommerce.shared.controlleradvice.uniqueconstraintvalidation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class UniqueConstraintExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UniqueConstraintErrorOutput warnAboutUniqueRecordConstraint(SQLIntegrityConstraintViolationException exception) {
        return new UniqueConstraintErrorOutput("O registro que você está tentando cadastrar já existe e não admite duplicidade");
    }
}
