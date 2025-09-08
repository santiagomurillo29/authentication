package co.com.bancolombia.usecase.user.exception;

import co.com.bancolombia.model.globalmessage.GlobalMessage;
import lombok.Getter;

@Getter
public class CoreException extends RuntimeException {
    private final GlobalMessage error;

    protected CoreException(GlobalMessage error) {
        super(error.getMessage());
        this.error = error;
    }
}
