package co.com.bancolombia.r2dbc.exception;

import co.com.bancolombia.model.user.globalmessage.GlobalMessage;
import co.com.bancolombia.usecase.user.exception.CoreException;

public class DataBaseException extends CoreException {
    public DataBaseException(GlobalMessage error){
        super(error);
    }
}



