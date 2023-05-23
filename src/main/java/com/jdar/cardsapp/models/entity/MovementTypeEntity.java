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
@Table(name = "MOVEMENTS_TYPES")
public class MovementTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "movement_description")
    private String movementDescription;

    @Column(name = "movement_label")
    private String movementLabel;

    @Column(name = "movement_name_key")
    private String movementNameKey;

}
