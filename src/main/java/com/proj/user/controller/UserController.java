package com.proj.user.controller;

import com.proj.user.business.UserService;
import com.proj.user.business.dto.AddressDTO;
import com.proj.user.business.dto.TelephoneDTO;
import com.proj.user.business.dto.UserDTO;
import com.proj.user.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                        userDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UserDTO> searchUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUserData(@RequestBody UserDTO userDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateUserData(token, userDTO));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO,
                                                    @RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.updateAddress(id, addressDTO));
    }

    @PutMapping("/telephone")
    public ResponseEntity<TelephoneDTO> updateTelephone(@RequestBody TelephoneDTO telephoneDTO,
                                                        @RequestHeader("id") Long id) {
        return ResponseEntity.ok(userService.updateTelephone(id, telephoneDTO));
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> registerAddres(@RequestBody AddressDTO addressDTO,
                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.registerAddress(token, addressDTO));
    }

    @PostMapping("/telphone")
    public ResponseEntity<TelephoneDTO> registerTelephone(@RequestBody TelephoneDTO telephoneDTO,
                                                          @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.registerTelephone(token, telephoneDTO));
    }
}