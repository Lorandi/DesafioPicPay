package com.DesafioPicPay.service;

import com.DesafioPicPay.dto.PersonDTO;
import com.DesafioPicPay.entity.Person;
import com.DesafioPicPay.helper.MessageHelper;
import com.DesafioPicPay.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

import static com.DesafioPicPay.enums.PersonTypeEnum.JURIDIC_PERSON;
import static com.DesafioPicPay.enums.PersonTypeEnum.PHYSICAL_PERSON;
import static com.DesafioPicPay.exception.ExceptionsEnum.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final MessageHelper messageHelper;

    public Person create(PersonDTO personDTO) {

        checkPersonTypeAndDocuments(personDTO);

        Person person = Person.builder()
                .name(personDTO.getName())
                .lastName(personDTO.getLastName())
                .password(personDTO.getPassword())
                .email(personDTO.getEmail())
                .cpf(personDTO.getCpf())
                .cnpj(personDTO.getCnpj())
                .personType(personDTO.getPersonType())
                .balance(personDTO.getBalance())
                .build();

        return personRepository.save(person);
    }

    public Person update(final Person person) {
        return personRepository.save(person);
    }

    public Person findById(final Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(messageHelper.get(ERROR_CPF_IS_REQUIRED, id));
                    return new ResponseStatusException(NOT_FOUND, messageHelper.get(ERROR_PERSON_NOT_FOUND, id));
                });
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void checkPersonTypeAndDocuments(PersonDTO personDTO) {
        if(personDTO.getPersonType() == null) {
            log.error(messageHelper.get(ERROR_PERSON_TYPE_IS_REQUIRED));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_PERSON_TYPE_IS_REQUIRED));
        }

        if (personDTO.getPersonType() == PHYSICAL_PERSON) {
            checkPhysicalPerson(personDTO);
            checkIfCpfIsAlreadyRegistered(personDTO.getCpf());
        } else {
            checkJuridicPerson(personDTO);
            checkIfCnpjIsAlreadyRegistered(personDTO.getCnpj());
        }
        checkIfEmailIsAlreadyRegistered(personDTO.getEmail());
    }


    public void checkPhysicalPerson(PersonDTO person) {
        if (person.getCpf() == null || person.getPersonType() == PHYSICAL_PERSON && person.getCpf().isEmpty()) {
            log.error(messageHelper.get(ERROR_CPF_IS_REQUIRED, person.getCpf()));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_CPF_IS_REQUIRED, person.getCpf()));
        }

        if (person.getPersonType() == PHYSICAL_PERSON && person.getCnpj() != null) {
            log.error(messageHelper.get(ERROR_PHYSICAL_PERSON_SHOULD_NOT_HAVE_CNPJ));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_PHYSICAL_PERSON_SHOULD_NOT_HAVE_CNPJ));
        }

    }

    public void checkJuridicPerson(PersonDTO person) {
        if (person.getCnpj() == null || person.getPersonType() == JURIDIC_PERSON && person.getCnpj().isEmpty()) {
            log.error(messageHelper.get(ERROR_CNPJ_IS_REQUIRED, person.getCpf()));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_CNPJ_IS_REQUIRED, person.getCpf()));
        }

        if (person.getPersonType() == JURIDIC_PERSON && person.getCpf() != null) {
            log.error(messageHelper.get(ERROR_JURIDIC_PERSON_SHOULD_NOT_HAVE_CPF));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_JURIDIC_PERSON_SHOULD_NOT_HAVE_CPF));
        }
    }

    public void checkSenderBalance(Long personId, BigDecimal value) {
        Person person = findById(personId);
        if (person.getBalance().compareTo(value) < 0) {
            log.error(messageHelper.get(ERROR_NOT_ENOUGH_BALANCE, personId));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_NOT_ENOUGH_BALANCE, personId));
        }
    }

    public void checkIfSenderIsNotJuridicPerson(Long personId) {
        Person person = findById(personId);
        if (person.getPersonType() == JURIDIC_PERSON) {
            log.error(messageHelper.get(ERROR_JURIDIC_PERSON_NOT_ALLOWED, personId));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_JURIDIC_PERSON_NOT_ALLOWED, personId));
        }
    }

    public void subtractFromTheAccount(Person person, BigDecimal value) {
        person.setBalance(person.getBalance().subtract(value));
        update(person);
    }

    public void addToTheAccount(Person person, BigDecimal value) {
        person.setBalance(person.getBalance().add(value));
        update(person);
    }

    public void checkIfEmailIsAlreadyRegistered(String email) {
        if (email !=null && personRepository.findByEmail(email).isPresent()) {
            log.error(messageHelper.get(ERROR_EMAIL_ALREADY_REGISTERED, email));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_EMAIL_ALREADY_REGISTERED, email));
        }
    }

    public void checkIfCpfIsAlreadyRegistered(String cpf) {
        if (cpf != null && personRepository.findByCpf(cpf).isPresent()) {
            log.error(messageHelper.get(ERROR_CPF_ALREADY_USED, cpf));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_CPF_ALREADY_USED, cpf));
        }
    }

    public void checkIfCnpjIsAlreadyRegistered(String cnpj) {
        if (cnpj != null && personRepository.findByCpf(cnpj).isPresent()) {
            log.error(messageHelper.get(ERROR_CNPJ_ALREADY_USED, cnpj));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_CNPJ_ALREADY_USED, cnpj));
        }
    }

}
