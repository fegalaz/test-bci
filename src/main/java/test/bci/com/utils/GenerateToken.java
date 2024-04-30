package test.bci.com.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class GenerateToken {
    public static String getToken() {

        String secretKey = "asdasdasd123";

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusDays(1);

        return Jwts.builder().setSubject("user").setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())) // Fecha
                .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }
}
