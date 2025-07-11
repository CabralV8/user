package com.proj.user.business;

import com.proj.user.business.converter.UserConverter;
import com.proj.user.business.dto.AddressDTO;
import com.proj.user.business.dto.TelephoneDTO;
import com.proj.user.business.dto.UserDTO;
import com.proj.user.infrastructure.entity.Address;
import com.proj.user.infrastructure.entity.Telephone;
import com.proj.user.infrastructure.entity.User;
import com.proj.user.infrastructure.exceptions.ConflictException;
import com.proj.user.infrastructure.exceptions.ResourceNotFoundException;
import com.proj.user.infrastructure.repositories.AddressRepository;
import com.proj.user.infrastructure.repositories.TelephoneRepository;
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
    private final AddressRepository addressRepository;
    private final TelephoneRepository telephoneRepository;

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

    public UserDTO searchUserByEmail(String email) {
        try {
            return userConverter.toUserDTO(
                    userRepository.findByEmail(email)
                            .orElseThrow(
                                    () -> new ResourceNotFoundException("Email not found " + email)
                            )
            );
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email not found " + email);
        }
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

    public AddressDTO updateAddress(Long idAddress, AddressDTO addressDTO){

        Address entity = addressRepository.findById(idAddress).orElseThrow(()->
                new ResourceNotFoundException("Id not found" + idAddress));

        Address address = userConverter.updateAddress(addressDTO, entity);

        return userConverter.toAddressDTO(addressRepository.save(address));
    }

    public TelephoneDTO updateTelephone(Long idTelephone, TelephoneDTO telephoneDTO){
        Telephone telephoneEntity = telephoneRepository.findById(idTelephone).orElseThrow(()->
                new ResourceNotFoundException("Id not found" + idTelephone));

        Telephone telephone = userConverter.updateTelephone(telephoneDTO, telephoneEntity);

        return userConverter.toTelephoneDTO(telephoneRepository.save(telephone));
    }

}
