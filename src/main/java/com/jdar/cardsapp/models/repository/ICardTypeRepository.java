package com.jdar.cardsapp.models.repository;

import com.jdar.cardsapp.models.entity.CardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICardTypeRepository extends JpaRepository<CardTypeEntity, UUID> {

    Optional<CardTypeEntity> findByCardTypeCode(String cardTypeCode);

}