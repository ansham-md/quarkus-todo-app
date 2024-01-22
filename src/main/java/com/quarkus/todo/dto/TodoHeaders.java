package com.quarkus.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoHeaders {

    private String moneyhubId;
    private String providerId;
    private String accountType;
    private String correlationId;

}
