package com.vn.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.service.IUserService;
import com.vn.vo.ProductVO;
import com.vn.vo.UsersVO;

@RestController
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @PreAuthorize(("isAuthenticated()"))
    @GetMapping("/user/info")
    public Authentication readAll(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    @PostMapping("/register")
    public UsersVO register(@RequestBody UsersVO vo) {

        vo.setPassword(encoder.encode(vo.getPassword()));
        userService.create(vo);
        return vo;
    }
    
}