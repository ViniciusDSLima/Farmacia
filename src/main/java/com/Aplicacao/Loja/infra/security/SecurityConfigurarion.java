package com.Aplicacao.Loja.infra.security;

import org.hibernate.cache.spi.support.CollectionNonStrictReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfigurarion {
    private final String [] cadastroUsuarios = {
            "/auth/login",
            "/auth/cadastro",
    };

    private final String [] gerenciamentoProdutos = {
            "/produto/cadastro",
            "/produto/atualizar",
            "/produto/{id}"
    };

    private final String [] pedidos = {
            "/pedidos/comprar",
            "/pedidos/atualizarPedido",
            "/pedidos",
            "/pedidos/{id}",
            "/pedidos/{id}"
    };

    private final String [] funcionarios = {
            "/funcionarios/cadastro",
            "/funcionarios/atualizar",
            "/funcionarios",
            "/funcionarios/{id}",
            "/funcionarios/{id}"
    };


    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(cadastroUsuarios).permitAll()
                        .requestMatchers(gerenciamentoProdutos).hasAnyRole("SUPERVISOR")
                        .requestMatchers(HttpMethod.GET,"/produto","/produto/{id}").hasAnyRole("CLIENTE")
                        .requestMatchers(pedidos).hasAnyRole("CLIENTE")
                        .requestMatchers(funcionarios).hasAnyRole("GERENTE")
                        .requestMatchers(HttpMethod.POST,"/clientes/cadastro").hasAnyRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT,"/clientes/atualizar").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/clientes").hasAnyRole("SUPERVISOR")
                        .requestMatchers(HttpMethod.GET,"/cliente/{id}").hasAnyRole("SUPERVISOR")
                        .requestMatchers(HttpMethod.DELETE, "cliente/{id}").hasAnyRole("CLIENTE")
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

    @Bean
    public Customizer<CorsConfigurer<HttpSecurity>> corsCustomize(){
        return (cors) -> cors.configurationSource(request -> {
            CorsConfiguration configuration  =  new CorsConfiguration();
            configuration.addAllowedOrigin("http://localhost:8080");
            configuration.addAllowedMethod("*");
            configuration.addAllowedHeader("*");
            configuration.setAllowCredentials(true);
            return configuration;
        });

    }

}
