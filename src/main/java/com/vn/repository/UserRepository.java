package com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.entity.Role;
import com.vn.entity.Users;
import com.vn.vo.RoleVO;
import com.vn.vo.UsersVO;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    Users findByUsernameAndPassword(String username, String password);
    @Query("SELECT r from Role r,UserRole ur,user u WHERE r.id= ur.role and ur.user = u.id and u.id = ?1")
    List<Role> findRole(Integer id);
}