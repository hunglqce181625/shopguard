package com.vnpt.shopguard.security;

import com.vnpt.shopguard.entity.User;
import com.vnpt.shopguard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ Thêm dòng này

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("===== [DEBUG] Bắt đầu kiểm tra đăng nhập =====");
        System.out.println("Email nhập vào: " + email);

        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("❌ Không tìm thấy tài khoản với email: " + email);
            throw new UsernameNotFoundException("Không tìm thấy tài khoản với email: " + email);
        }

        System.out.println("✅ Tìm thấy tài khoản:");
        System.out.println(" → ID: " + user.getId());
        System.out.println(" → Email: " + user.getEmail());
        System.out.println(" → ROLE: " + user.getRole());
        System.out.println(" → BCrypt Hash DB: " + user.getPassword());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())  // → Spring sẽ tự thêm "ROLE_" → Ví dụ ADMIN → ROLE_ADMIN
                .build();

    }
}
