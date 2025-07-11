package com.vnpt.shopguard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ShopguardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopguardApplication.class, args);
		String rawPassword = "Auth123@";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("BCrypt Hash: " + hashedPassword);
		
		System.out.println(new BCryptPasswordEncoder().matches("admin123", "$2a$10$zByVHqBpHTi7m9pisytKu.wTB4bUg08xNn26TVDhkI5XAcpzINIuK"));
		// Kết quả: true nếu khớp

	}

}
