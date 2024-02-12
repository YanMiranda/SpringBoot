package med.vol.api.infra.records;

import org.springframework.validation.FieldError;

public record DadosErrorValidation(String campo, String message) {

    public DadosErrorValidation(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
