package com.vn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String address;

    private Boolean status;

    private UsersVO user;

    private List<OrderDetailVO> orderdetails;

}