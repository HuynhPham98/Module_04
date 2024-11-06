package com.ra.repository;

import com.ra.model.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustormerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);
}
