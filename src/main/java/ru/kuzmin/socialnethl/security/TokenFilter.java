package ru.kuzmin.socialnethl.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kuzmin.socialnethl.repository.SessionRepository;

import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {
    private final SessionRepository sessionRepository;

    public TokenFilter(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        var auth = request.getHeader("Authorization");
        var token = auth.split("Bearer ")[1];
        var userId = sessionRepository.getSessionToken(token);
        if (userId != null) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(401);
        }
    }
}
