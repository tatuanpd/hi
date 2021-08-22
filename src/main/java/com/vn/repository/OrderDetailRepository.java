package com.vn.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entity.OrderDetail;
import com.vn.entity.OrderdetailPK;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderdetailPK> {
}