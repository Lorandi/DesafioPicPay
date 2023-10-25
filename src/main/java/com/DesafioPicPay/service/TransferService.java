package com.DesafioPicPay.service;

import com.DesafioPicPay.dto.TransferDTO;
import com.DesafioPicPay.entity.Person;
import com.DesafioPicPay.entity.Transfer;
import com.DesafioPicPay.helper.MessageHelper;
import com.DesafioPicPay.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.DesafioPicPay.exception.ExceptionsEnum.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {

    private final PersonService personService;
    private final TransferRepository transferRepository;
    private final RestTemplate restTemplate;
    private final MessageHelper messageHelper;

    @Transactional
    public Transfer createTransfer(TransferDTO transferDTO) {
        Person sender = personService.findById(transferDTO.getSenderId());
        Person receiver = personService.findById(transferDTO.getReceiverId());

        validateTransfer(transferDTO);
        checkTransferAuthorization();

        Transfer transfer = transferRepository.save(Transfer.builder()
                .senderId(sender)
                .receiverId(receiver)
                .value(transferDTO.getValue())
                .date(LocalDateTime.now())
                .build());

        personService.subtractFromTheAccount(sender, transferDTO.getValue());
        personService.addToTheAccount(receiver, transferDTO.getValue());

        return transfer;
    }

    public Transfer findById(final Long id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(messageHelper.get(ERROR_TRANSFER_NOT_FOUND, id));
                    return new ResponseStatusException(NOT_FOUND, messageHelper.get(ERROR_TRANSFER_NOT_FOUND, id));
                });
    }

    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    public void validateTransfer(TransferDTO transferDTO) {
        if (transferDTO.getValue().signum() < 0) {
            log.error(messageHelper.get(ERROR_TRANSFER_VALUE_NEGATIVE));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_TRANSFER_VALUE_NEGATIVE));
        }
        personService.checkIfSenderIsNotJuridicPerson(transferDTO.getSenderId());
        personService.checkSenderBalance(transferDTO.getSenderId(), transferDTO.getValue());
    }

    public void checkTransferAuthorization() {
        ResponseEntity<Map> response = restTemplate.getForEntity("https://run.mocky.io/v3/5d7ea9fc-845f-4b03-ab18-b18e81099649", Map.class);
        boolean isAuthorized = response.getStatusCode().equals(HttpStatus.OK) &&
                Objects.requireNonNull(response.getBody()).get("message").equals("Autorizado");
        log.info(isAuthorized ? "Transação autorizada" : "Transação não autorizada");
        if (!isAuthorized) {
            log.error(messageHelper.get(ERROR_TRANSFER_NOT_ALLOWED));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_TRANSFER_NOT_ALLOWED));
        }
    }
}
