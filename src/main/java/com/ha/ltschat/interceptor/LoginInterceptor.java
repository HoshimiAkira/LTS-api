package com.ha.ltschat.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {

        String jwt = request.getParameter("token");
        if (jwt != null && !jwt.isEmpty()) {
            try {

                Jws<Claims> claims = Jwts.parser()
                        .setSigningKey("abcdfghia0bcdfghiabcd1fghiabcdfghizuynmobtuop")
                        .parseClaimsJws(jwt.replace("Bearer ", ""));

                Claims jwtClaims = claims.getBody();
                return true;
            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
                return false;
            }
        } else {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing JWT");
            return false;
        }
    }
}
