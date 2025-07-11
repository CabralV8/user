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
                .addressLine2(dto.getAddress_line_2())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostal_code())
                .build();
    }

    public AddressDTO toAddressDTO(Address entity) {
        return AddressDTO.builder()
                .street(entity.getStreet())
                .number(entity.getNumber())
                .address_line_2(entity.getAddressLine2())
                .city(entity.getCity())
                .state(entity.getState())
                .postal_code(entity.getPostalCode())
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
}
