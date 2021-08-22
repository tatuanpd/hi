package com.vn.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the product database table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer avaiable;

    private String createDate;

    private String image;

    private String name;

    private Double price;


    private CategoryVO category;

}