package com.restaurant.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant.model.Menu;
import com.restaurant.restaurant.repository.MenuRepository;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

       @Transactional(readOnly = true)
    public List<Menu> findByIdSite(Long idSite) {
        return menuRepository.findByFirstConfiguration_Id(idSite);
    }

        @Transactional(readOnly = true)
    public Optional<Menu> findByIdSiteSingleMenu(Long idSite, String tokenMenu) {
        return menuRepository.findByFirstConfiguration_IdAndTokenMenu(idSite, tokenMenu);
    }
    @Transactional
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id); 
    }
}
