package com.nick.myApp.controllers;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.nick.myApp.dto.LoginRequest;
import com.nick.myApp.models.Users;
import com.nick.myApp.repos.UsersRepo;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://pathfinderio.netlify.app")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor

public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UsersRepo usersRepo;

    // obtain secret key and expiration time from application.properties
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-days:120}")
    private long expirationDays;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @GetMapping("/")
    public Map<String, String> healthCheck() {
        return Map.of("status", "UP", "message", "PathFinder API is running");
    }

    @GetMapping("/debug/users")
    public ResponseEntity<?> debugUsers() {
        return ResponseEntity.ok(usersRepo.findAll());
    }

    // 檢查這個帳號的密碼 hash 是什麼 (方便確認是不是明文)
    @GetMapping("/debug/user/{identifier}")
    public ResponseEntity<?> debugUser(@PathVariable String identifier) {
        if (identifier.contains("@")) {
            return ResponseEntity.ok(usersRepo.findByEmailIgnoreCase(identifier));
        } else {
            return ResponseEntity.ok(usersRepo.findByMobile(identifier));
        }
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // 驗證帳號密碼
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getIdentifier(),
                            request.getPassword()));

            String identifier = authentication.getName();
            System.out.println("Identifier from authentication: " + identifier);

            // 判斷係 email 定 mobile
            Users user;
            if (identifier.contains("@")) {
                user = usersRepo.findByEmailIgnoreCase(identifier)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            } else {
                user = usersRepo.findByMobile(identifier)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }

            // 生成 JWT
            long expirationTime = expirationDays * 24 * 60 * 60 * 1000L;
            Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

            String jwt = Jwts.builder()
                    .setSubject(identifier)
                    .claim("firstname", user.getFirstname())
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(getSigningKey())
                    .compact();

            return ResponseEntity.ok(Map.of(
                    "message", "Login Successful",
                    "token", jwt,
                    "identifier", identifier, // 👈 加返呢個
                    "firstname", user.getFirstname(),
                    "welcome", "Welcome, " + user.getFirstname()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("Error", "Invalid email, mobile or password"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(Map.of("Error", "User not found"));
        } catch (Exception e) {
            e.printStackTrace(); // Debug log
            return ResponseEntity.status(500).body(Map.of("Error", "Internal Server Error"));
        }
    }

}
