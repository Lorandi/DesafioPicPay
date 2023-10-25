package com.DesafioPicPay.dto;

import com.DesafioPicPay.enums.PersonTypeEnum;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Jacksonized
@Builder
public class PersonDTO {
    String name;
    String lastName;
    String password;
    String email;
    String cpf;
    String cnpj;
    BigDecimal balance;
    PersonTypeEnum personType;
}
