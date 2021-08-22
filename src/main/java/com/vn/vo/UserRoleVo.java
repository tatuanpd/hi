package com.vn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private RoleVO role;

    private UsersVO user;
}