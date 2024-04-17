package com.renatoganske.errorHandling.exception.business;

import com.renatoganske.errorHandling.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends BaseRuntimeException {
    private static final String KEY = "notfound";

    public ObjectNotFoundException() {
        super();
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
