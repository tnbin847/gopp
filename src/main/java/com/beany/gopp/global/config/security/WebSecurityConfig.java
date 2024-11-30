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
 * 스프링 시큐리티 설정 클래스
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 패스워드 암호화 객체 스프링 빈 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 보안 필터를 적용할 필요가 없는 리소스를 설정
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
        http.csrf().disable()       // CSRF 보호 비활성화
            .httpBasic().disable()  // HTTP 기본 인증 비활성화
            .formLogin().disable(); // 폼 기반 로그인 비활성화

        /* 요청에 대한 인증 및 인가 처리 설정 */
        http.authorizeRequests()
                .antMatchers(SecurityConstants.PUBLICY_URL_MATCHERS).permitAll()
                .anyRequest().authenticated();

        /* 세션 정책 설정 */
        http.sessionManagement()
                .maximumSessions(1) // 최대 허용 가능한 세션 수 설정 (-1 : 무제한 로그인 세션 허용)
                .maxSessionsPreventsLogin(true); // 동시 로그인 차단 (false : 기존 세션 만료)

        /* h2-console 사용을 위해 동일 도메인에서 iframe 접근이 가능하도록 X-Frame-Options 설정 */
        http.headers().frameOptions().sameOrigin();
    }
}