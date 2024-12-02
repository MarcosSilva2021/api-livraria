package com.devm.api_livraria.filter;

import com.devm.api_livraria.service.JWTService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class FiltroDoToken extends GenericFilterBean {

    public static final int TOKEN_INDEX = 7;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpservletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String header = httpservletRequest.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou mal formatado");
            return;
        }

        String token = header.substring(TOKEN_INDEX);

        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(JWTService.TOKEN_KEY).build();
            parser.parseClaimsJws(token).getBody();
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | PrematureJwtException | IllegalArgumentException | ExpiredJwtException e){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        chain.doFilter(request, response);
    }
}
