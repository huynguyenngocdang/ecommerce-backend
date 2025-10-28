package com.app.ecom_application.services;

import com.app.ecom_application.dto.userDto.AppUserDTO;
import com.app.ecom_application.dto.userDto.request.AddUserREQ;
import com.app.ecom_application.dto.userDto.request.UpdateUserREQ;

import java.util.List;
import java.util.Optional;

public interface IAppUserServices {
    Optional<AppUserDTO> addUser(AddUserREQ addUserReq);
    List<AppUserDTO> getUsers();
    Optional<AppUserDTO> getUserById(Long id);
    Optional<AppUserDTO> updateUser(Long id, UpdateUserREQ updateUserReq);
    Optional<AppUserDTO> deleteUser(Long id);
}
