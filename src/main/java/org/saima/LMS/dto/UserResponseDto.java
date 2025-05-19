package org.saima.LMS.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponseDto {

    private String name;
    private String email;
    private String phone;
    private Integer creditBalance;

}