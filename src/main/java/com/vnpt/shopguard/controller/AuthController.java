package com.vnpt.shopguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vnpt.shopguard.entity.User;
import com.vnpt.shopguard.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private boolean isStrongPassword(String password) {
        if (password == null) return false;
        if (password.length() < 7) return false;
        if (!password.matches(".*[A-Z].*")) return false;  // Chứa chữ hoa
        if (!password.matches(".*[a-z].*")) return false;  // Chứa chữ thường
        if (!password.matches(".*\\d.*")) return false;   // Chứa số
        return true;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng.");
        }
        if (logout != null) {
            model.addAttribute("message", "Đăng xuất thành công.");
        }
        return "site/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "site/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email đã được sử dụng.");
            return "site/register";
        }
        
        // Validate độ mạnh mật khẩu
        if (!isStrongPassword(user.getPassword())) {
            model.addAttribute("error", "Mật khẩu phải từ 8 ký tự, chứa chữ hoa, chữ thường và số.");
            return "site/register";
        }

        // Đăng ký user (service sẽ mã hóa mật khẩu)
        userService.register(user);

        // Thêm thông báo thành công → Giữ nguyên trang
        model.addAttribute("message", "Đăng ký thành công! Bạn có thể đăng nhập ngay.");

        // Reset form
        model.addAttribute("user", new User());

        return "site/register";  // ⚠️ Không redirect → ở lại trang /auth/register
    }


}
