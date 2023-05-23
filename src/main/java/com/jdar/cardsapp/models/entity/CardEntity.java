package com.jdar.cardsapp.models.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CARDS")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "CARD_DUE_DATE")
    private String cardDueDate;

    @Column(name = "CARD_IS_ACTIVE")
    private int cardIsActive;

    @Column(name = "CARD_IS_BLOCK")
    private int cardIsBlock;

    @Column(name = "CARD_BALANCE")
    private BigDecimal cardBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_TYPE_ID", nullable = false)
    private CardTypeEntity cardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_OWNER_ID", nullable = false)
    private UserEntity user;

}
