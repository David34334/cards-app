package com.jdar.cardsapp.models.repository;

import com.jdar.cardsapp.models.entity.CardMovementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICardMovementTypeRepository extends JpaRepository<CardMovementTypeEntity, UUID> {

}