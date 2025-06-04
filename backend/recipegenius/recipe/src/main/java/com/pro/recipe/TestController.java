package com.pro.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipeXD")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public String findAll(Authentication authentication) {
        return "Test from recipe + " + authentication.getName();
    }

}
