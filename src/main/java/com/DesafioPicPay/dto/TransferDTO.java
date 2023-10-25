package com.DesafioPicPay.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class TransferDTO {
    Long senderId;
    Long receiverId;
    BigDecimal value;
}
