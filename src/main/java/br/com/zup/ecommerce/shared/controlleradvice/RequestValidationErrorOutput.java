package br.com.zup.ecommerce.shared.controlleradvice;

import java.util.ArrayList;
import java.util.List;

public class RequestValidationErrorOutput {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<RequestFieldErrorOutput> fieldErrorOutputs = new ArrayList<>();

    public void addGlobalErrorMessage(String errorMessage) {
        globalErrorMessages.add(errorMessage);
    }

    public void addFieldError(String field, String message) {
        RequestFieldErrorOutput fieldErrorOutput = new RequestFieldErrorOutput(field, message);
        fieldErrorOutputs.add(fieldErrorOutput);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<RequestFieldErrorOutput> getFieldErrorOutputs() {
        return fieldErrorOutputs;
    }
}
