package com.ra.service.customer;

import com.ra.exception.ResourceNotFoundException;
import com.ra.model.dto.customer.rep.CustomerResponseDTO;
import com.ra.model.dto.customer.req.CustomerRequestDTO;
import com.ra.model.entity.customer.Customer;
import com.ra.repository.CustormerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustormerRepository custormerRepository;

    @Override
    public List<CustomerResponseDTO> findAll() {
        List<Customer> customers = custormerRepository.findAll();
        List<CustomerResponseDTO> responseDTOs;
        responseDTOs = customers.stream().map(customer -> CustomerResponseDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .birthday(customer.getBirthday()).build()).toList();
        return responseDTOs;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer();
        customer.setFullName(customerRequestDTO.getFullName());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setPassword(customerRequestDTO.getPassword());
        customer.setBirthday(customerRequestDTO.getBirthday());

        Customer newCustomer = custormerRepository.save(customer);
        return CustomerResponseDTO.builder()
                .id(newCustomer.getId())
                .fullName(newCustomer.getFullName())
                .email(newCustomer.getEmail())
                .birthday(newCustomer.getBirthday())
                .build();
    }

    @Override
    public CustomerResponseDTO findById(Long id) {
        Customer customer = custormerRepository.findById(id).orElse(null);
        if (customer == null) {
            // Bạn có thể ném ra một ngoại lệ tùy chỉnh hoặc trả về một giá trị mặc định
            throw new ResourceNotFoundException("Khách hàng không tìm thấy với ID: " + id);
        }
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setId(id);
        responseDTO.setFullName(customer.getFullName());
        responseDTO.setEmail(customer.getEmail());
        responseDTO.setBirthday(customer.getBirthday());
        return responseDTO;
    }
}
