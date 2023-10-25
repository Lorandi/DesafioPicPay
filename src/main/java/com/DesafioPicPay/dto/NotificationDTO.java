package com.DesafioPicPay.dto;

import lombok.Value;


@Value
public class NotificationDTO {
    String email;
    String message;

    public String toString(){
        return email + ": " +  message;
    }
}
