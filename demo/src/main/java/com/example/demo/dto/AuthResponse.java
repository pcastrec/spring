package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthResponse {
    
    private String token;
    private String type = "Bearer";
    
    private Long id;
    private String role;
    private String email;
    private String username;
}
