package com.DesafioPicPay.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transfer")
@Table(name = "transfer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Person senderId;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Person receiverId;

    private BigDecimal value;

    private LocalDateTime date;

}
