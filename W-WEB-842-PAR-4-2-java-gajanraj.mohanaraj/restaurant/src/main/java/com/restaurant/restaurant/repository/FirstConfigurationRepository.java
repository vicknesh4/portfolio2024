package com.restaurant.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant.model.FirstConfiguration;

@Repository
public interface FirstConfigurationRepository extends JpaRepository<FirstConfiguration, Long> {
    Optional<FirstConfiguration> findByToken(String token);
    
}
