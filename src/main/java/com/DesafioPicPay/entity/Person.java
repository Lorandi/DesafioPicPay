package com.DesafioPicPay.entity;

import com.DesafioPicPay.enums.PersonTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "person")
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String document;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private PersonTypeEnum personType;




}
