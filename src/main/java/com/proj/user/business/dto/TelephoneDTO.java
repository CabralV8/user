package com.proj.user.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelephoneDTO {

    private Long id;
    private String number;
    private String ddd;

}
