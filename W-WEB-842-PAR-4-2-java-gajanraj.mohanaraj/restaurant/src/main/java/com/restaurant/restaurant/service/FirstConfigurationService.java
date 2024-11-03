package com.restaurant.restaurant.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.restaurant.model.FirstConfiguration;
import com.restaurant.restaurant.repository.FirstConfigurationRepository;

@Service
public class FirstConfigurationService {

    @Autowired
    private FirstConfigurationRepository firstConfigurationRepository;

    public FirstConfiguration createFirstConfiguration(FirstConfiguration firstConfiguration) {
        return firstConfigurationRepository.save(firstConfiguration);
    }

     public Optional<FirstConfiguration> findByToken(String token) {
        return firstConfigurationRepository.findByToken(token);
    }

    public Optional<FirstConfiguration> updateCard(String token) {
        Optional<FirstConfiguration> existingConfigOptional = firstConfigurationRepository.findByToken(token);

        if (existingConfigOptional.isPresent()) {
            FirstConfiguration existingConfig = existingConfigOptional.get();
            existingConfig.setCard(1); 

            
            firstConfigurationRepository.save(existingConfig);

            return existingConfigOptional;
        } else {
            return Optional.empty(); 
        }

    }

    public Optional<FirstConfiguration> updateMenu(String token) {
        Optional<FirstConfiguration> existingConfigOptional = firstConfigurationRepository.findByToken(token);

        if (existingConfigOptional.isPresent()) {
            FirstConfiguration existingConfig = existingConfigOptional.get();
            existingConfig.setIs_menu(1); 

            
            firstConfigurationRepository.save(existingConfig);

            return existingConfigOptional;
        } else {
            return Optional.empty(); 
        }

    }

    public Optional<FirstConfiguration> updatePlats(String token) {
        Optional<FirstConfiguration> existingConfigOptional = firstConfigurationRepository.findByToken(token);
    
        if (existingConfigOptional.isPresent()) {
            FirstConfiguration existingConfig = existingConfigOptional.get();
            existingConfig.setIs_plats(1);
    
            firstConfigurationRepository.save(existingConfig);
    
            return existingConfigOptional;
        } else {
            return Optional.empty();
        }
    }
    


    
    public Optional<FirstConfiguration> updateFirstConfiguration(String token, FirstConfiguration updatedConfiguration) {
        Optional<FirstConfiguration> existingConfigOptional = firstConfigurationRepository.findByToken(token);
        
        if (existingConfigOptional.isPresent()) {
            FirstConfiguration existingConfig = existingConfigOptional.get();
            existingConfig.setName(updatedConfiguration.getName());
            existingConfig.setUrl(updatedConfiguration.getUrl());
            existingConfig.setColorTitle(updatedConfiguration.getColorTitle());
            existingConfig.setColorButton(updatedConfiguration.getColorButton());
            existingConfig.setMenu(updatedConfiguration.getMenu());
            existingConfig.setPassword(updatedConfiguration.getPassword());
    
         
            if (updatedConfiguration.getPhoto() != null) {
                existingConfig.setPhoto(updatedConfiguration.getPhoto());
            }
    
            FirstConfiguration updatedConfig = firstConfigurationRepository.save(existingConfig);
            return Optional.of(updatedConfig);
        } else {
            return Optional.empty();
        }
    }
    

}
