package com.proj.user.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Long number;

    @Column(name = "address_line_2")
    @JsonProperty("address_line_2")
    private String addressLine2;

    @Column(name = "city", length = 120)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "postal_code", length = 9)
    @JsonProperty("postal_code")
    private String postalCode;

    @Column(name = "user_id")
    private Long user_id;
}
