package com.vnpt.shopguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vnpt.shopguard.service.ProductService;

@Controller
@RequestMapping("/products")  // ✅ Đặt URL chuẩn RESTful
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ Trang danh sách sản phẩm
    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "Sản phẩm");
        model.addAttribute("products", productService.findAll());
        return "site/product";
    }

    // ✅ Trang chi tiết sản phẩm theo ID
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        try {
            model.addAttribute("product", productService.findById(id));
            return "site/product-detail";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "site/error-404";  // ⚠️ Cần tạo view error-404.html → chuyên nghiệp
        }
    }
}
