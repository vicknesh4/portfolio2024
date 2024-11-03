package com.restaurant.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant.model.Plats;
import com.restaurant.restaurant.repository.PlatsRepository;

@Service
public class PlatsService {

    @Autowired
    private PlatsRepository platsRepository;

    public Plats createPlat(Plats plat) {
        return platsRepository.save(plat);
    }

    public Optional<Plats> getPlatById(Long id) {
        return platsRepository.findById(id);
    }

    public List<Plats> getAllPlats() {
        return platsRepository.findAll();
    }

    public Plats updatePlat(Long id, Plats platDetails) {
        Optional<Plats> platOptional = platsRepository.findById(id);
        if (platOptional.isPresent()) {
            Plats plat = platOptional.get();
            plat.setNom(platDetails.getNom());
            plat.setPhoto(platDetails.getPhoto());
            plat.setDescription(platDetails.getDescription());
            plat.setPrix(platDetails.getPrix());
            plat.setAllergenes(platDetails.getAllergenes());
            return platsRepository.save(plat);
        } else {
            return null; 
        }
    }

    public void deletePlat(Long id) {
        platsRepository.deleteById(id);
    }

           @Transactional(readOnly = true)
    public List<Plats> findByIdSite(Long idSite) {
        return platsRepository.findByFirstConfiguration_Id(idSite);
    }

    public Optional<Plats> findByIdSiteSinglePlat(Long idSite, String tokenPlat) {
        return platsRepository.findByFirstConfiguration_IdAndTokenPlat(idSite, tokenPlat);
    }

}
