package com.vnpt.shopguard.controller;

import com.vnpt.shopguard.entity.User;
import com.vnpt.shopguard.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService; // ⚠️ Dùng AdminUserService thay vì UserService

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("title", "Người dùng");
        model.addAttribute("users", adminUserService.findAll()); // ✅ Không còn lỗi đỏ
        return "admin/user-list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        User user = adminUserService.findById(id); // ✅ Không còn lỗi đỏ
        if (user == null) {
            model.addAttribute("error", "Không tìm thấy người dùng.");
            return "admin/user-detail";
        }
        model.addAttribute("user", user);
        return "admin/user-detail";
    }
}
