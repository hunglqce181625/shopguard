package com.vnpt.shopguard.controller;

import com.vnpt.shopguard.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("title", "Đơn hàng");
        model.addAttribute("orders", orderService.findAll());
        return "admin/order-list"; // ⚠️ Tạo file admin/order-list.html
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        var order = orderService.findById(id);
        if (order == null) {
            model.addAttribute("error", "Không tìm thấy đơn hàng.");
            return "admin/order-detail"; // ⚠️ Tạo file admin/order-detail.html
        }
        model.addAttribute("order", order);
        return "admin/order-detail";
    }
}
