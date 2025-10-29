package com.app.ecom_application.dto.userDto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserREQ {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}
