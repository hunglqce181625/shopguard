package com.vnpt.shopguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vnpt.shopguard.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("title", "Trang chủ");
        model.addAttribute("products", productService.findAll());
        return "site/home";
    }

    @GetMapping("about")
    public String about(Model model) {
        model.addAttribute("title", "Giới thiệu");
        return "site/about";
    }
}