package ru.kuzmin.socialnethl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.kuzmin.socialnethl.repository.SessionRepository;
import ru.kuzmin.socialnethl.security.TokenFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.kuzmin.socialnethl.repository")
public class SecurityConfig {
    @Autowired
    SessionRepository sessionRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .anyRequest().permitAll()
                )
                .csrf().disable();
        return http.build();
    }

    @Bean
    public FilterRegistrationBean<TokenFilter> logFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenFilter(sessionRepository));
        registrationBean.addUrlPatterns("/user/**");
        return registrationBean;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("123")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
