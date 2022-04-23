package com.gdufe.cs.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUserDTO<T> {
    Integer code;
    String message;
    T Data;
}
