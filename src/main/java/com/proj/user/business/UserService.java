package com.proj.user.business;

import com.proj.user.business.converter.UserConverter;
import com.proj.user.business.dto.UserDTO;
import com.proj.user.infrastructure.entity.User;
import com.proj.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO){
        User user = userConverter.toUserEntity(userDTO);
        return userConverter.toUserDTO(userRepository.save(user));
    }
}
