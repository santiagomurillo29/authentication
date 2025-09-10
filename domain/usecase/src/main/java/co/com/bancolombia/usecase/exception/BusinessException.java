package co.com.bancolombia.usecase.exception;

import co.com.bancolombia.model.globalmessage.GlobalMessage;

public class BusinessException extends CoreException{
    public BusinessException(GlobalMessage error) {
        super(error);
    }
}
