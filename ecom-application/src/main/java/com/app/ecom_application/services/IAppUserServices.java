package com.app.ecom_application.services;

import com.app.ecom_application.dto.userDto.AppUserDTO;
import com.app.ecom_application.dto.userDto.request.AddUserREQ;
import com.app.ecom_application.dto.userDto.request.UpdateUserREQ;

import java.util.List;
import java.util.Optional;

public interface IAppUserServices {
    AppUserDTO addUser(AddUserREQ addUserReq);
    List<AppUserDTO> getUsers();
    AppUserDTO getUserById(Long id);
    AppUserDTO updateUser(Long id, UpdateUserREQ updateUserReq);
    AppUserDTO deleteUser(Long id);
}
