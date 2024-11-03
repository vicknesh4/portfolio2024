package com.restaurant.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant.model.Plats;

@Repository
public interface PlatsRepository extends JpaRepository<Plats, Long> {
     List<Plats> findByFirstConfiguration_Id(Long idSite);
     Optional<Plats> findByFirstConfiguration_IdAndTokenPlat(Long id, String tokenPlat);
}
