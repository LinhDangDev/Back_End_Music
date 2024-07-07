package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Menu;
import com.soundFinal.sound_final.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus(){ return menuRepository.findAll(); }

    public Menu getMenuById(Integer id){ return menuRepository.findById(id).orElse(null); }

    public void addOrUpdateMenu(Menu menu){ menuRepository.save(menu); }

    public void deleteMenuById(Integer id){ menuRepository.deleteById(id); }
}
