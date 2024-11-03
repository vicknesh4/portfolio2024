package com.restaurant.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByFirstConfiguration_Id(Long idSite);
    Optional<Card> findByFirstConfiguration_IdAndTokenCard(Long idSite, String tokenCard);


}
