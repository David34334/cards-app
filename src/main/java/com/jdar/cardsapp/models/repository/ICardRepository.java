package com.jdar.cardsapp.models.repository;

import com.jdar.cardsapp.models.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICardRepository extends JpaRepository<CardEntity, UUID> {

    Optional<CardEntity> findByCardNumber(String cardNumber);

}