package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Menu;
import com.soundFinal.sound_final.service.MenuService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public ApiResponse<List<Menu>> getAll(){
        return ApiResponse.<List<Menu>>builder()
                .result(menuService.getAllMenus())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Menu> getMenu(@PathVariable Integer id){
        return ApiResponse.<Menu>builder()
                .result(menuService.getMenuById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addMenu(@Valid @RequestBody Menu menu, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("Menu add failed: " + result)
                    .build();
        menuService.addOrUpdateMenu(menu);
        return ApiResponse.<String>builder()
                .result("Menu has been added")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> updateMenu(@Valid @RequestBody Menu menu ,@PathVariable Integer id, BindingResult result){
        Menu findMenu = menuService.getMenuById(id);
        if(result.hasErrors() || findMenu == null)
            return ApiResponse.<String>builder()
                    .result("Menu update failed: " + result)
                    .build();
        menuService.addOrUpdateMenu(menu);
        return ApiResponse.<String>builder()
                .result("Menu has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMenu(@PathVariable Integer id){
        menuService.deleteMenuById(id);
        return ApiResponse.<String>builder()
                .result("Menu has been deleted")
                .build();
    }
}
