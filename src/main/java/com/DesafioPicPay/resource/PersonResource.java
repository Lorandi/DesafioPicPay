package com.DesafioPicPay.resource;

import com.DesafioPicPay.dto.PersonDTO;
import com.DesafioPicPay.entity.Person;
import com.DesafioPicPay.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonResource {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO) {
//        return ResponseEntity.ok(service.create(personDTO));

        return new ResponseEntity<>(service.create(personDTO), CREATED);
    }

    @GetMapping
    public List<Person> findAll() {
        return ResponseEntity.ok(service.findAll()).getBody();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id)).getBody();
    }
}
