package com.app.ecom_application.dto.userDto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserREQ {
    private String username;
    private String firstName;
    private String lastName;
}
