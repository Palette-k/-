package com.gdufe.cs.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDirectorDTO <T> {
    Integer code;
    String message;
    T Data;

}
