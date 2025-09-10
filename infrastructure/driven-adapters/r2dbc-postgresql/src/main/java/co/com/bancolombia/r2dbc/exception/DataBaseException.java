package co.com.bancolombia.r2dbc.exception;

import co.com.bancolombia.model.globalmessage.GlobalMessage;
import co.com.bancolombia.usecase.exception.CoreException;

public class DataBaseException extends CoreException {
    public DataBaseException(GlobalMessage error){
        super(error);
    }
}



