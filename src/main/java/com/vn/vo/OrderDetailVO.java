package com.vn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private OrderdetailPKVO id;

    private Double price;

    private Integer quantity;

    private ProductVO product;

    private OrderVO order;

}