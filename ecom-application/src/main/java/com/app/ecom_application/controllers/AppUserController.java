package com.app.ecom_application.controllers;

import com.app.ecom_application.dto.userDto.AppUserDTO;
import com.app.ecom_application.dto.userDto.request.AddUserREQ;
import com.app.ecom_application.dto.userDto.request.UpdateUserREQ;
import com.app.ecom_application.services.IAppUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final IAppUserServices appUserServices;

    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getUsers() {
        return ResponseEntity.ok(appUserServices.getUsers());
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> addUser(@RequestBody AddUserREQ addUserReq) {
        AppUserDTO saved = appUserServices.addUser(addUserReq).orElse(null);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        return appUserServices.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @RequestBody UpdateUserREQ updateUserREQ) {
        return appUserServices.updateUser(id, updateUserREQ)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUserDTO> deleteUser(@PathVariable Long id) {
        return appUserServices.deleteUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
