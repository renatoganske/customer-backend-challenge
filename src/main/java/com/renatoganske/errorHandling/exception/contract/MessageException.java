package com.renatoganske.errorHandling.exception.contract;

import java.util.Map;

public interface MessageException {
    String getExceptionKey();

    Map<String, Object> getMapDetails();
}
