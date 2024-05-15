package com.study.kioskbackend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig  {

    private final JwtUtil jwtConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //csrf보안 비활성화한다. RESTful API 서버이므로
                .csrf( auth -> auth.disable() )
                .cors(auth->auth.configurationSource(corsConfigurationSource()));

        http     //HTTP요청에 대한 보안설정을 시작한다.
                .authorizeHttpRequests( (auth) -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                );

        //스프링 시큐리티에서 세션 관리 상태가 없음으로 구성한다.
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ));

        //jwt 보안필터를 추가한다.
        //특정 필터 앞에 추가한다.
        http.addFilterBefore(new JwtAuthenticationFilter(jwtConfig),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //CORS 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedHeaders(Collections.singletonList("*")); // 허용할 HTTP header
        config.setAllowedMethods(Collections.singletonList("*")); // 허용할 HTTP method
        config.setAllowedOriginPatterns(Collections.singletonList("*")); // 허용할 출처
        config.setAllowCredentials(true); // 쿠키 인증 요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}