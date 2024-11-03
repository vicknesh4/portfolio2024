package com.restaurant.restaurant.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
     List<Menu> findByFirstConfiguration_Id(Long idSite);
      Optional<Menu> findByFirstConfiguration_IdAndTokenMenu(Long idSite, String tokenMenu);
}