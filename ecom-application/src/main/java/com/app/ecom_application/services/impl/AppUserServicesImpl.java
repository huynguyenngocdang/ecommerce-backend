package com.app.ecom_application.services.impl;

import com.app.ecom_application.dto.userDto.AppUserDTO;
import com.app.ecom_application.dto.userDto.request.AddUserREQ;
import com.app.ecom_application.dto.userDto.request.UpdateUserREQ;
import com.app.ecom_application.entity.Address;
import com.app.ecom_application.entity.AppUser;
import com.app.ecom_application.exception.ResourceNotFoundException;
import com.app.ecom_application.repository.AppUserRepository;
import com.app.ecom_application.services.IAppUserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServicesImpl implements IAppUserServices {

    private final ObjectMapper mapper;
    private final AppUserRepository appUserRepository;

    @Override
    public AppUserDTO addUser(AddUserREQ addUserReq) {
        return Optional.of(addUserReq)
                .map(addUser -> mapper.convertValue(addUser, AppUser.class))
                .map(user -> {
                    if(appUserRepository.existsByUsername(user.getUsername())) {
                        throw new IllegalArgumentException("Username is already in use");
                    }
                    return user;
                })
                .map(appUserRepository::save)
                .map(saved -> mapper.convertValue(saved, AppUserDTO.class))
                .orElse(null);
    }

    @Override
    public List<AppUserDTO> getUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(appUser -> mapper.convertValue(appUser, AppUserDTO.class))
                .toList();
    }

    @Override
    public AppUserDTO getUserById(Long id) {
        return appUserRepository.findById(id)
                .map(appUser -> mapper.convertValue(appUser, AppUserDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public AppUserDTO updateUser(Long id, UpdateUserREQ updateUserReq) {
        return appUserRepository.findById(id)
                .filter(appUser -> appUser.getUsername().equals(updateUserReq.getUsername()))
                .map(existingAppUser -> {
                    existingAppUser.setFirstName(updateUserReq.getFirstName());
                    existingAppUser.setLastName(updateUserReq.getLastName());
                    appUserRepository.save(existingAppUser);
                    return mapper.convertValue(existingAppUser, AppUserDTO.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public AppUserDTO deleteUser(Long id) {
        return appUserRepository.findById(id)
                .map(user -> {
                    appUserRepository.delete(user);
                    return mapper.convertValue(user, AppUserDTO.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
