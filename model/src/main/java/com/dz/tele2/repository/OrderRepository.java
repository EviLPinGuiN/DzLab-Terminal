package com.dz.tele2.repository;

import com.dz.tele2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex on 29.09.15.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
