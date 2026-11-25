package com.example.academia.config;

import com.example.academia.repository.IPessoaRepository;
import com.example.academia.security.JwtAuthenticationFilter;
import com.example.academia.security.JwtService;
import com.example.academia.security.SecurityErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtService jwtService,
                                           IPessoaRepository ipessoaRepository,
                                           SecurityErrorHandler errorHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(errorHandler)
                        .accessDeniedHandler(errorHandler))
                .authorizeHttpRequests(auth -> auth
                        // Públicos
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/alunos/autocadastro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/planos/**", "/modalidades/**").permitAll()

                        // Somente admin
                        .requestMatchers("/planos/**", "/modalidades/**").hasRole("ADMIN")
                        .requestMatchers("/funcionarios/**").hasRole("ADMIN")

                        // Funcionário e admin
                        .requestMatchers(HttpMethod.POST, "/alunos").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET, "/alunos").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PATCH, "/alunos/*/desativar", "/alunos/*/reativar").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/auth/contas/*").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/pagamentos").hasRole("FUNCIONARIO")

                        // Demais rotas: autenticado. Regras finas (propriedade) ficam no Service.
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, ipessoaRepository),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
