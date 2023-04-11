package com.ha.ltschat.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

import java.util.Date;
import io.jsonwebtoken.security.Keys;
public class JwtUtil {

    private String jwtSecret="abcdfghia0bcdfghiabcd1fghiabcdfghizuynmobtuop";


    private long jwtExpiration= 3600;

    public String generateJwtToken(String username, String type) {
        Date now =new Date();
        Date expiration=new Date(now.getTime()+1000*jwtExpiration);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        String jwtToken = Jwts.builder()
                .setSubject(username)
                .claim("type", type)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(signatureAlgorithm,jwtSecret)
                .compact();

        return jwtToken;
    }
}
