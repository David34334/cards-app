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
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "TRANSACTION_NAME")
    private String transactionName;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;

    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;

    @Column(name = "TRANSACTION_IS_BLOCK")
    private int transactionIsBlock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSACTION_TYPE_ID", nullable = false)
    private TransactionTypeEntity transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSACTION_CARD_ID", nullable = false)
    private CardEntity transactionCard;

}
