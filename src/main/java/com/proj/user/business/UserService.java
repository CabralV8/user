package com.proj.user.business;

import com.proj.user.business.converter.UserConverter;
import com.proj.user.business.dto.UserDTO;
import com.proj.user.infrastructure.entity.User;
import com.proj.user.infrastructure.exceptions.ConflictException;
import com.proj.user.infrastructure.exceptions.ResourceNotFoundException;
import com.proj.user.infrastructure.repositories.UserRepository;
import com.proj.user.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserDTO saveUser(UserDTO userDTO){
        existentEmail(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.toUserEntity(userDTO);
        return userConverter.toUserDTO(userRepository.save(user));
    }

    public void existentEmail(String email){
        try {
            boolean exists = verifyExistentEmail(email);
            if (exists) {
                throw new ConflictException("Email already registered: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email already registered: ", e.getCause());
        }
    }

    public boolean verifyExistentEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User searchUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email not found" + email));
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public UserDTO updateUserData(String token, UserDTO userDTO){
        String email = jwtUtil.extractEmailToken(token.substring(7));

        userDTO.setPassword(userDTO.getPassword() != null ? passwordEncoder.encode(userDTO.getPassword()) : null);
        User userEntity = userRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email not located."));

        User user = userConverter.updateUser(userDTO, userEntity);


        return userConverter.toUserDTO(userRepository.save(userEntity));
    }

}
