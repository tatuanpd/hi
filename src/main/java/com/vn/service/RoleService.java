package com.vn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entity.Role;
import com.vn.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> readAll() {
		List<Role> listRoles= new ArrayList<Role>();
		listRoles= roleRepository.findAll();
		for (Role role : listRoles) {
			role.setUserRoles(null);
		}
		return listRoles;
	}
}
