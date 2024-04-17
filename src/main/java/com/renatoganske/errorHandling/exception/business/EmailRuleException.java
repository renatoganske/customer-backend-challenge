package com.renatoganske.errorHandling.exception.business;

import com.renatoganske.errorHandling.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailRuleException extends BaseRuntimeException {
    private static final String KEY = "email.rule";

    public EmailRuleException() {
        super();
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
