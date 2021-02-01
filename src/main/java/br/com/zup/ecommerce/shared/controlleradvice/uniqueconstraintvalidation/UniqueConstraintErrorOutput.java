package br.com.zup.ecommerce.shared.controlleradvice.uniqueconstraintvalidation;

public class UniqueConstraintErrorOutput {
    private String message;

    public UniqueConstraintErrorOutput(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
