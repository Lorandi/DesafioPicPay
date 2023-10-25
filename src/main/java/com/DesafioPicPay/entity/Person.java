package com.DesafioPicPay.entity;

import com.DesafioPicPay.enums.PersonTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "person")
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Builder
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
    private String cpf;

    @Column(unique = true)
    private String cnpj;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private PersonTypeEnum personType;




}
