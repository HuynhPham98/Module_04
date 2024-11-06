package com.ra.model.dto.customer.rep;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponseDTO {
    private Long id;
    private String email;
    private String fullName;
    private LocalDate birthday;
}
