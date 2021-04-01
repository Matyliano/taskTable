package matyliano.configuration.security;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtAuthenticationEntryPoint extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void configure(HttpSecurity http) {
        JwtRequestFilter customFilter = new JwtRequestFilter(jwtTokenUtil);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
