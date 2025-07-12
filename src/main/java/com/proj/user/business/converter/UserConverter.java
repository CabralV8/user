package com.proj.user.business.converter;

import com.proj.user.business.dto.AddressDTO;
import com.proj.user.business.dto.TelephoneDTO;
import com.proj.user.business.dto.UserDTO;
import com.proj.user.infrastructure.entity.Address;
import com.proj.user.infrastructure.entity.Telephone;
import com.proj.user.infrastructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    // User
    public User toUserEntity(UserDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .addresses(toAddressEntityList(dto.getAddresses()))
                .telephones(toTelephoneEntityList(dto.getTelephones()))
                .build();
    }

    public UserDTO toUserDTO(User entity) {
        return UserDTO.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .addresses(toAddressDTOList(entity.getAddresses()))
                .telephones(toTelephoneDTOList(entity.getTelephones()))
                .build();
    }

    // Address
    public Address toAddressEntity(AddressDTO dto) {
        return Address.builder()
                .street(dto.getStreet())
                .number(dto.getNumber())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .build();
    }

    public AddressDTO toAddressDTO(Address entity) {
        return AddressDTO.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .addressLine2(entity.getAddressLine2())
                .city(entity.getCity())
                .state(entity.getState())
                .postalCode(entity.getPostalCode())
                .build();
    }

    public List<Address> toAddressEntityList(List<AddressDTO> dtos) {
        List<Address> list = new ArrayList<>();
        for (AddressDTO dto : dtos) {
            list.add(toAddressEntity(dto));
        }
        return list;
    }

    public List<AddressDTO> toAddressDTOList(List<Address> entities) {
        List<AddressDTO> list = new ArrayList<>();
        for (Address entity : entities) {
            list.add(toAddressDTO(entity));
        }
        return list;
    }

    // Telephone
    public Telephone toTelephoneEntity(TelephoneDTO dto) {
        return Telephone.builder()
                .number(dto.getNumber())
                .ddd(dto.getDdd())
                .build();
    }

    public TelephoneDTO toTelephoneDTO(Telephone entity) {
        return TelephoneDTO.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .ddd(entity.getDdd())
                .build();
    }

    public List<Telephone> toTelephoneEntityList(List<TelephoneDTO> dtos) {
        List<Telephone> list = new ArrayList<>();
        for (TelephoneDTO dto : dtos) {
            list.add(toTelephoneEntity(dto));
        }
        return list;
    }

    public List<TelephoneDTO> toTelephoneDTOList(List<Telephone> entities) {
        List<TelephoneDTO> list = new ArrayList<>();
        for (Telephone entity : entities) {
            list.add(toTelephoneDTO(entity));
        }
        return list;
    }

    public User updateUser(UserDTO userDTO, User userEntity){
        return User.builder()
                .name(userDTO.getName() != null ? userDTO.getName() : userEntity.getName())
                .id(userEntity.getId())
                .password(userDTO.getPassword() != null ? userDTO.getPassword() : userEntity.getPassword())
                .email(userDTO.getEmail() != null ? userDTO.getEmail() : userEntity.getEmail())
                .addresses(userEntity.getAddresses())
                .telephones(userEntity.getTelephones())
                .build();
    }

    public Address updateAddress(AddressDTO addressDTO, Address addressEntity){
        return Address.builder()
                .id(addressEntity.getId())
                .street(addressDTO.getStreet() != null ? addressDTO.getStreet() : addressEntity.getStreet())
                .number(addressDTO.getNumber() != null ? addressDTO.getNumber() : addressEntity.getNumber())
                .city(addressDTO.getCity() != null ? addressDTO.getCity() : addressEntity.getCity())
                .postalCode(addressDTO.getPostalCode() != null ? addressDTO.getPostalCode() : addressEntity.getPostalCode())
                .addressLine2(addressDTO.getAddressLine2() != null ? addressDTO.getAddressLine2() : addressEntity.getAddressLine2())
                .state(addressDTO.getState() != null ? addressDTO.getState() : addressEntity.getState())
                .build();
    }

    public Telephone updateTelephone(TelephoneDTO telephoneDTO, Telephone telephoneEntity){
        return Telephone.builder()
                .id(telephoneEntity.getId())
                .ddd(telephoneDTO.getDdd() != null ? telephoneDTO.getDdd() : telephoneEntity.getDdd())
                .number(telephoneDTO.getNumber() != null ? telephoneDTO.getNumber() : telephoneEntity.getNumber())
                .build();
    }

    public Address toAddressEntity(AddressDTO addressDTO, Long userId){
        return Address.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .postalCode(addressDTO.getPostalCode())
                .addressLine2(addressDTO.getAddressLine2())
                .state(addressDTO.getState())
                .number(addressDTO.getNumber())
                .user_id(userId)
                .build();
    }

    public Telephone toTelephoneEntity(TelephoneDTO telephoneDTO, Long userId){
        return Telephone.builder()
                .number(telephoneDTO.getNumber())
                .ddd(telephoneDTO.getDdd())
                .user_id(userId)
                .build();
    }

}
