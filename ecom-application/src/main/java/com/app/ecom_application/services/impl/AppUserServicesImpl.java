package com.app.ecom_application.services.impl;

import com.app.ecom_application.dto.userDto.AppUserDTO;
import com.app.ecom_application.dto.userDto.request.AddUserREQ;
import com.app.ecom_application.dto.userDto.request.UpdateUserREQ;
import com.app.ecom_application.entity.AppUser;
import com.app.ecom_application.services.IAppUserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServicesImpl implements IAppUserServices {
    private final List<AppUser> users = new ArrayList<>();
    private Long userId = 1L;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Optional<AppUserDTO> addUser(AddUserREQ addUserReq) {
        AppUser appUser = mapper.convertValue(addUserReq, AppUser.class);
        appUser.setId(userId++);
        users.add(appUser);
        return Optional.of(mapper.convertValue(appUser, AppUserDTO.class));
    }

    @Override
    public List<AppUserDTO> getUsers() {
        return users.stream().map(user -> mapper.convertValue(user, AppUserDTO.class))
                .toList();
    }

    @Override
    public Optional<AppUserDTO> getUserById(Long id) {
        return users.stream().filter(appUser -> appUser.getId().equals(id))
                .findFirst()
                .map(appUser -> mapper.convertValue(appUser, AppUserDTO.class));
    }

    @Override
    public Optional<AppUserDTO> updateUser(Long id, UpdateUserREQ updateUserReq) {
        return users.stream().filter(appUser -> appUser.getUsername().equals(updateUserReq.getUsername())
                && appUser.getId().equals(id))
                .findFirst()
                .map(existingAppUser -> {
                    existingAppUser.setFirstName(updateUserReq.getFirstName());
                    existingAppUser.setLastName(updateUserReq.getLastName());
                    return mapper.convertValue(existingAppUser, AppUserDTO.class);
                });
    }

    @Override
    public Optional<AppUserDTO> deleteUser(Long id) {
        return users.stream().filter(appUser -> appUser.getId().equals(id))
                .findFirst()
                .map(appUser -> {
                    users.remove(appUser);
                    return mapper.convertValue(appUser, AppUserDTO.class);
                });
    }
}
