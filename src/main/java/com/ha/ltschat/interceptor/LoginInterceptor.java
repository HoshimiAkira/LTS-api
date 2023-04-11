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
        System.out.println(jwt);
        if (jwt != null && !jwt.isEmpty()) {
            try {

                Jws<Claims> claims = Jwts.parser()
                        .setSigningKey("abcdfghia0bcdfghiabcd1fghiabcdfghizuynmobtuop")
                        .parseClaimsJws(jwt.replace("Bearer ", ""));

                Claims jwtClaims = claims.getBody();

                // 在这里可以根据需要进行JWT的各种验证，例如验证过期时间、验证签发者、验证主题等
                // ...

                return true;
            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
                return false;
            }
        } else {
            // 请求头中未携带JWT，可以在这里处理缺少JWT的情况，例如返回错误响应
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing JWT");
            return false;
        }
    }
}
