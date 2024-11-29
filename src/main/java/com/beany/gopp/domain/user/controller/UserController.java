package com.beany.gopp.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 박 수 빈
 * @version 1.0.0
 */

@RestController
public class UserController {

    /**
     * 회원가입 페이지 호출
     */
    @GetMapping("/sign-up")
    public ModelAndView signUp() {
        return new ModelAndView("app/user/signup");
    }
}