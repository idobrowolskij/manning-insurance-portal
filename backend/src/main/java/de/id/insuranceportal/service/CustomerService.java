package de.id.insuranceportal.service;

import de.id.insuranceportal.dto.customer.CustomerCreation;
import de.id.insuranceportal.dto.customer.CustomerUpdate;
import de.id.insuranceportal.dto.customer.CustomerView;
import de.id.insuranceportal.dto.mapper.CustomerMapper;
import de.id.insuranceportal.dto.mapper.RefResolver;
import de.id.insuranceportal.entity.Customer;
import de.id.insuranceportal.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RefResolver resolver;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, RefResolver resolver) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.resolver = resolver;
    }

    public CustomerView create(CustomerCreation dto) {
        Customer c = customerMapper.fromCreation(dto, resolver);
        return customerMapper.toView(customerRepository.save(c));
    }

    public List<CustomerView> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::toView).toList();
    }

    public CustomerView getById(UUID id) {
        return customerMapper.toView(customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found: " + id)));
    }

    @Transactional
    public CustomerView update(UUID id, CustomerUpdate dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found: " + id));
        customerMapper.applyUpdate(dto, existing, resolver);
        return customerMapper.toView(existing); // transactional
    }

    public void delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            log.error("Customer not found for delete. id={}", id);
            throw new NoSuchElementException("Customer not found: " + id);
        }
        customerRepository.deleteById(id);
    }

}
