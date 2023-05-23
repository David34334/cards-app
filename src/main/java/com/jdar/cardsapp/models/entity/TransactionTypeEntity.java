package com.jdar.cardsapp.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTIONS_TYPES")
public class TransactionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "TRANSACTION_TYPE_NAME")
    private String transactionTypeName;

    @Column(name = "TRANSACTION_TYPE_DESCRIPTION")
    private String transactionTypeDescription;

    @Column(name = "TRANSACTION_TYPE_NAME_KEY")
    private String transactionTypeNameKey;

}
