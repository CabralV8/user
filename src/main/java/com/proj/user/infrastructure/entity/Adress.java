package com.proj.user.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adress")
public class Adress {


        private String street;
    private String number;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
}
