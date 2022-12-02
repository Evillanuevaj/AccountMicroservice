package com.bootcamp.java.account.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    @JsonIgnore
    private String id;

    @NotBlank(message="Number Account cannot be null or empty")
    private String identityNumber;

    @NotBlank(message="Client be null or empty")
    private String idClient;

    @NotBlank(message="Account type cannot be null or empty")
    private String typeAccount;

    @NotBlank(message="Balance cannot be null or empty")
    private String balance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOpening;
}
