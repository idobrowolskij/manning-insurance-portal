package de.id.insuranceportal.service;

import de.id.insuranceportal.entity.Customer;
import de.id.insuranceportal.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements CrudService<Customer> {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer obj) {
        return customerRepository.save(obj);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer update(UUID id, Customer obj) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Customer not found for update. id={}", id);
                    return new NoSuchElementException("Customer not found: " + id);
                });

        if (obj.getEmail() != null) {
            existing.setEmail(obj.getEmail());
        }

        return customerRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            log.error("Customer not found for delete. id={}", id);
            throw new NoSuchElementException("Customer not found: " + id);
        }
        customerRepository.deleteById(id);
    }

}
