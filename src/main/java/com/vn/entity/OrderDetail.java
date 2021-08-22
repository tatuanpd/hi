package com.vn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderdetailPK id;

    private Double price;

    private Integer quantity;

    //bi-directional many-to-one association to Product
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    //bi-directional many-to-one association to Order
    @ManyToOne
    @JoinColumn(name = "orders_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order orders;

}