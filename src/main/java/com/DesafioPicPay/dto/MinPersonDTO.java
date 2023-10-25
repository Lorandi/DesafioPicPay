package com.DesafioPicPay.dto;

import com.DesafioPicPay.enums.PersonTypeEnum;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class MinPersonDTO {
    Long id;
    String name;
    String personType;
}
