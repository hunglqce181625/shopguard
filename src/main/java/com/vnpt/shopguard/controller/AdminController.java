package com.vnpt.shopguard.controller;

import com.vnpt.shopguard.service.CategoryService;
import com.vnpt.shopguard.service.OrderService;
import com.vnpt.shopguard.service.ProductService;
import com.vnpt.shopguard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private CategoryService categoryService;
    @Autowired private ProductService productService;
    @Autowired private OrderService orderService;
    @Autowired private UserService userService;

    @GetMapping("")
    public String dashboard(Model model) {
        model.addAttribute("title", "Bảng điều khiển");

        model.addAttribute("categoryCount", categoryService.findAll().size());
        model.addAttribute("productCount", productService.findAll().size());
        model.addAttribute("orderCount", orderService.findAll().size());
        model.addAttribute("userCount", userService.findAll().size());

        return "admin/dashboard";
    }
}
