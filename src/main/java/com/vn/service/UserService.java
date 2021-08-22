package com.vn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vn.entity.Role;
import com.vn.entity.UserRole;
import com.vn.entity.Users;
import com.vn.repository.RoleRepository;
import com.vn.repository.UserRepository;
import com.vn.repository.UserRoleRepository;
import com.vn.vo.UsersVO;

@Service
public class UserService implements IUserService , UserDetailsService{

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    
	
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UsersVO create(UsersVO vo) {
    	Users entity = new Users();
		BeanUtils.copyProperties(vo, entity);
		usersRepository.save(entity);
		vo.setId(entity.getId());
		
		List<Role> inputRole = vo.getRoles();
		
		List<UserRole> userRoles = inputRole.stream().map(e -> {
			UserRole userRole = new UserRole();
			
			Optional<Role> optional = roleRepository.findById(e.getId());
			if(optional.isPresent()) {
				userRole.setRole(optional.get());
			}
			userRole.setUser(entity);
			return userRole; 
		}).collect(Collectors.toList());
		
		userRoleRepository.saveAll(userRoles);
		return vo;
    }

    @Override
    public List<UsersVO> readAll(String search) {
        List<UsersVO> voList = new ArrayList<>();
        List<Users> usersList = usersRepository.findAll();
        for (Users users : usersList) {
            UsersVO vo = new UsersVO();
            List<Role> role= usersRepository.findRole(users.getId());
            for (Role a : role) {
				a.setUserRoles(null);
			}
            System.out.println(role);
            BeanUtils.copyProperties(users, vo);
            vo.setRoles(role);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public UsersVO update(UsersVO vo) {
    	Users entity = new Users(); 
		BeanUtils.copyProperties(vo, entity);
		usersRepository.save(entity);
		vo.setId(entity.getId());
		
//		List<Role> inputRole = vo.getRoles();
//		
//		List<UserRole> userRoles = inputRole.stream().map(e -> {
//			UserRole userRole = new UserRole();
//			
//			Optional<Role> optional = roleRepository.findById(e.getId());
//			if(optional.isPresent()) {
//				userRole.setRole(optional.get());
//			}
//			userRole.setUser(entity);
//			return userRole; 
//		}).collect(Collectors.toList());
//		
//		userRoleRepository.saveAll(userRoles);
		return vo;
    }

    @Override
    public UsersVO delete(Integer id) {
        UsersVO vo = new UsersVO();
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isPresent()) {
            Users users = optional.get();
            BeanUtils.copyProperties(users, vo);
            usersRepository.delete(users);
        }
        return vo;
    }

    @Override
    public UsersVO getById(Integer id) {
        UsersVO vo = new UsersVO();
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isPresent()) {
            Users users = optional.get();
            BeanUtils.copyProperties(users, vo);
        }
        return vo;
    }
    @Override
    public boolean existById(Integer id) {
        return usersRepository.existsById(id);
    }


	@Override
    public Users findUser(String user) {
        return usersRepository.findByUsername(user);

    }

	@Override
    public Users findByUsernameAndPassword(String username, String password) {
        Users users = usersRepository.findByUsernameAndPassword(username, password);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        user.setUserRoles(userRoleRepository.findAllByUserId(user.getId()));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomerUserDetails(user);
    }


	
    
}
