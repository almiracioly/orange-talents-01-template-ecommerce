package br.com.zup.ecommerce.shared.controlleradvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RequestValidationExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RequestValidationErrorOutput warnAboutValidationErrors(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();

        return buildValidationErrors(fieldErrors, objectErrors);
    }

    private RequestValidationErrorOutput buildValidationErrors(List<FieldError> fieldErrors, List<ObjectError> objectErrors) {
        RequestValidationErrorOutput validationErrors = new RequestValidationErrorOutput();

        objectErrors.forEach(error -> validationErrors.addGlobalErrorMessage(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String message = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), message);
        });

        return validationErrors;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
