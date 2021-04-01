package matyliano.configuration.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import matyliano.exception.notFound.TokenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter{

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtil.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenUtil.validateToken(token)) {
                Authentication auth = jwtTokenUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (TokenException ex) {
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(Integer.parseInt(ex.getMessage()));
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
