package com.renatoganske.errorHandling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDto {

    private String key;
    private String message;

}
