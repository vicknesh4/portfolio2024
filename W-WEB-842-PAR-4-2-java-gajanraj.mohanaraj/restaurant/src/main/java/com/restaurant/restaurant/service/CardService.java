package com.restaurant.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant.model.Card;
import com.restaurant.restaurant.repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Transactional(readOnly = true)
    public List<Card> findByIdSite(Long idSite) {
        return cardRepository.findByFirstConfiguration_Id(idSite);
    }

    @Transactional(readOnly = true)
    public Optional<Card> findByIdSiteSingleCard(Long idSite, String tokenCard) {
        return cardRepository.findByFirstConfiguration_IdAndTokenCard(idSite, tokenCard);
    }

    @Transactional
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
