package com.DesafioPicPay.repository;

import com.DesafioPicPay.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);
    Optional<Person> findByEmail(String email);
    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByCnpj(String cnpj);
}
