package com.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.entity.Role;
import com.vn.service.RoleService;
import com.vn.vo.ProductVO;


@CrossOrigin("*")
@RestController()
@RequestMapping("role")
public class RoleController {

	
	@Autowired
	RoleService roleservice;
	
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> readAll() {
        return ResponseEntity.ok(roleservice.readAll());
    }
	
}
