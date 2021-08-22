package com.vn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;

    private Boolean status;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users user;

    //bi-directional many-to-one association to Orderdetail
    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderdetails;

}