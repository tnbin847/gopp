package com.beany.gopp.global.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 박 수 빈
 * @version 1.0.0
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 비밀번호 암호화 객체를 스프링 빈으로 등록한다.
     * @return  BCryptPasswordEncoder 객체
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 보안 필터를 적용할 필요가 없는 리소스 설정
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * HTTP 요청에 대한 웹 기반 보안 구성
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* REST API 방식의 로그인 처리를 위한 비활성화 설정 */
        http.httpBasic().disable()
            .csrf().disable()
            .formLogin().disable()
            // 동일 도메인에서 iframe 접근이 가능하도록 X-Frame-Options 설정
            .headers().frameOptions().sameOrigin();
        /* 특정 HTTP 요청에 대한 인증 및 인가 처리 설정 */
        http.authorizeRequests()
                .antMatchers(SecurityConstants.PUBLICY_URL_MATCHERS).permitAll()
                .anyRequest().authenticated();
        /* 세션 정책 설정 */
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);
    }
}