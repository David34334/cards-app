package com.jdar.cardsapp.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CARDS_TYPES")
public class CardTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "CARD_TYPE_CODE")
    private String cardTypeCode;

    @Column(name = "CARD_TYPE_DESCRIPTION")
    private String cardTypeDescription;

    @Column(name = "CARD_TYPE_NAME_KEY")
    private String cardTypeNameKey;

}
