package com.ra.service.customer;

import com.ra.model.dto.customer.rep.CustomerResponseDTO;
import com.ra.model.dto.customer.req.CustomerRequestDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerService {
    List<CustomerResponseDTO> findAll();
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO findById(Long id);
}
