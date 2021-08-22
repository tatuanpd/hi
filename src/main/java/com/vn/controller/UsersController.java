package com.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vn.entity.Role;
import com.vn.entity.Users;
import com.vn.repository.UserRepository;
import com.vn.service.IUserService;
import com.vn.vo.ProductVO;
import com.vn.vo.RoleVO;
import com.vn.vo.UsersVO;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	IUserService userService;
	@Autowired
	UserRepository userRepository;
	 @Autowired
	 private BCryptPasswordEncoder encoder;
	 @Autowired
	    PasswordEncoder passwordEncoder;	
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<List<UsersVO>> readAll(@RequestParam(value = "search", required = false) String search) {
        return ResponseEntity.ok(userService.readAll(search));
    }
    
    @PostMapping("/create")
    public UsersVO create(@RequestBody UsersVO vo) {

        vo.setPassword(encoder.encode(vo.getPassword()));
        userService.create(vo);
        return vo;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<UsersVO> update(@RequestBody UsersVO vo) {
        if (!userService.existById(vo.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.update(vo));
    }
    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsersVO> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
   
}
