package com.DesafioPicPay.enums;

import com.DesafioPicPay.enums.serializer.EnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonSerialize(using = EnumSerializer.class)
@AllArgsConstructor
@Getter
public enum PersonTypeEnum implements EnumDescription {
    PHYSICAL_PERSON("Pessoa física"),
    JURIDIC_PERSON("Pessoa jurídica");

    private final String description;
}
