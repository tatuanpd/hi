package com.vn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the product database table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer avaiable;

    @Column(name = "create_date")
    private String createDate;

    private String image;

    private String name;

    private Double price;

    //bi-directional many-to-one association to Orderdetail
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderdetails;

    //bi-directional many-to-one association to Category
    @ManyToOne
    private Category category;

}