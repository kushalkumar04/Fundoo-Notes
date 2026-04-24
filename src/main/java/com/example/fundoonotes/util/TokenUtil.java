package com.example.fundoonotes.util;

/*
 * Simple Token Utility
 * Generates and decodes Base64 token using userId
 */



import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class TokenUtil {

    public String generateToken(Long userId) {
        return Base64.getEncoder().encodeToString(String.valueOf(userId).getBytes());
    }

    public Long decodeToken(String token) {
        String decoded = new String(Base64.getDecoder().decode(token));
        return Long.parseLong(decoded);
    }
}