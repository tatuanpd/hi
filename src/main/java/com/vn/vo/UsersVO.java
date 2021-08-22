package com.vn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import com.vn.entity.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Boolean active;

    private String email;

    private String name;

    private String password;

    private String photo;

    private String username;
    private String token;
    private List<OrderVO> orders;

    private List<Role> roles;


}