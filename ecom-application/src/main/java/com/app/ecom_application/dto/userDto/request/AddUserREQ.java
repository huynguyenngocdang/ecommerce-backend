package com.app.ecom_application.dto.userDto.request;

import com.app.ecom_application.dto.userDto.AddressDTO;
import com.app.ecom_application.entity.Address;
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
public class AddUserREQ {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
