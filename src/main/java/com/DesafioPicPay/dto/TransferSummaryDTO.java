package com.DesafioPicPay.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class TransferSummaryDTO {

    Long id;
    MinPersonDTO senderId;
    MinPersonDTO receiverId;
    BigDecimal value;
    LocalDateTime date;
}
