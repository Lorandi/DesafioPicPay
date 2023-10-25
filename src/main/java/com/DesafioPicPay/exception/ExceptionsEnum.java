package com.DesafioPicPay.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionsEnum {
    ERROR_GENERIC_EXCEPTION("error.generic.exception"),
    ERROR_CPF_ALREADY_USED("error.cpf.already.used"),
    ERROR_PERSON_NOT_FOUND("error.person.not.found"),
    ERROR_INVALID_CPF("error.invalid.cpf"),
    ERROR_CNPJ_ALREADY_USED("error.cnpj.already.used"),
    ERROR_INVALID_CNPJ("error.invalid.cnpj"),
    ERROR_DATE_FORMAT("error.date.format"),
    ERROR_CPF_IS_REQUIRED("error.cpf.is.required"),
    ERROR_CNPJ_IS_REQUIRED("error.cnpj.is.required"),
    ERROR_NOT_ENOUGH_BALANCE("error.not.enough.balance"),
    ERROR_JURIDIC_PERSON_NOT_ALLOWED("error.juridic.person.not.allowed"),
    ERROR_TRANSFER_NOT_ALLOWED("error.transfer.not.allowed"),
    ERROR_TRANSFER_VALUE_NEGATIVE("error.transfer.value.negative"),
    ERROR_TRANSFER_NOT_FOUND("error.transfer.not.found"),
    ERROR_EMAIL_ALREADY_REGISTERED("error.email.already.registered"),
    ERROR_JURIDIC_PERSON_SHOULD_NOT_HAVE_CPF("error.juridic.person.should.not.have.cpf"),
    ERROR_PHYSICAL_PERSON_SHOULD_NOT_HAVE_CNPJ("error.physical.person.should.not.have.cnpj"),
    ERROR_PERSON_TYPE_IS_REQUIRED("error.person.type.is.required"),
    ERROR_EMAIL_IS_REQUIRED("error.email.is.required"),
    ERROR_START_BALANCE_IS_REQUIRED("error.start.balance.is.required");


    private final String messageKey;
}
