package com.vn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private List<ProductVO> products;
}