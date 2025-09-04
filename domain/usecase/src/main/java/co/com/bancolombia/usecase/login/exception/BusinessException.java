package co.com.bancolombia.usecase.auth.exception;

import co.com.bancolombia.model.user.globalmessage.GlobalMessage;
import co.com.bancolombia.usecase.user.exception.CoreException;

public class BusinessException extends CoreException {
    public BusinessException(GlobalMessage error) {
        super(error);
    }
}
