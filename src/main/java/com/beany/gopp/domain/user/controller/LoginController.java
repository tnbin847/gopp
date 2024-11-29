package com.beany.gopp.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 박 수 빈
 * @version 1.0.0
 */

@Controller
public class LoginController {

    /**
     * 로그인 페이지 호출
     */
    @GetMapping("/")
    public String login() {
        return "app/index";
    }
}