package com.Aplicacao.Loja.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurarion {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/cadastro").permitAll()
                        .requestMatchers(HttpMethod.POST,"/produto/cadastro").hasAnyRole("ROLE_SUPERVISOR")
                        .requestMatchers(HttpMethod.PUT, "/produto/atualizar").hasAnyRole("ROLE_SUPERVISOR")
                        .requestMatchers(HttpMethod.GET, "/produto").hasAnyRole("ROLE_CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/produto/{id}").hasAnyRole("ROLE_CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/produto/{id}").hasAnyRole("ROLE_SUPERVISOR")
                        .requestMatchers(HttpMethod.POST,"/pedidos/comprar").hasAnyRole("ROLE_CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/pedidos/atualizarPedido").hasAnyRole("ROLE_cliente")
                        .requestMatchers(HttpMethod.GET,"/pedidos").hasAnyRole("ROLE_CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/pedidos/{id}").hasAnyRole("ROLE_CLIENTE")
                        .requestMatchers(HttpMethod.DELETE,"/pedidos/{id}").hasAnyRole("ROLE_CLIENTE")
                        .requestMatchers(HttpMethod.POST,"/funcionarios/cadastro").hasAnyRole("ROLE_GERENTE")
                        .requestMatchers(HttpMethod.PUT,"/funcionarios/atualizar").hasAnyRole("ROLE_GERENTE")
                        .requestMatchers(HttpMethod.GET,"/funcionarios").hasRole("ROLE_GERENTE")
                        .requestMatchers(HttpMethod.GET,"/funcionarios/{id}").hasAnyRole("ROLE_GERENTE")
                        .requestMatchers(HttpMethod.DELETE,"/funcionarios/{id}").hasAnyRole("ROLE_GERENTE")
                        .requestMatchers(HttpMethod.POST,"/clientes/cadastro").hasAnyRole("ROLE_USUARIO")
                        .requestMatchers(HttpMethod.PUT,"/clientes/atualizar").hasRole("ROLE_CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/clientes").hasAnyRole("ROLE_SUPERVISOR")
                        .requestMatchers(HttpMethod.GET,"/cliente/{id}").hasAnyRole("ROLE_SUPERVISOR")
                        .requestMatchers(HttpMethod.DELETE, "cliente/{id}").hasAnyRole("ROLE_CLIENTE")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
