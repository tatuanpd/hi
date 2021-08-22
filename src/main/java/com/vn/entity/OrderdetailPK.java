package com.vn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderdetailPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "orders_id", insertable = false, updatable = false)
    private Integer orderId;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

}