package com.DesafioPicPay.helper;

import com.DesafioPicPay.exception.ExceptionsEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;

import javax.annotation.PostConstruct;
import java.util.Locale;

@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String get(ExceptionsEnum code, Object... args) {
        return accessor.getMessage(code.getMessageKey(), args);
    }

    public String get(String code, Object... args) {
        try{
            return accessor.getMessage(code, args);
        }catch (NoSuchMessageException e){
            return code;
        }
    }
}
