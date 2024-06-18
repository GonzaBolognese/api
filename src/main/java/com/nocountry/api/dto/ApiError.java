package com.nocountry.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiError implements Serializable {

    private String backendMessage;

    private String message;

    private String url;

    private String method;

    private List<String> subsMessages;

    private LocalDateTime time;

}
