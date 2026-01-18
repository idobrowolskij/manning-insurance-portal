package de.id.insuranceportal.controller;

import de.id.insuranceportal.dto.customer.CustomerCreation;
import de.id.insuranceportal.dto.customer.CustomerUpdate;
import de.id.insuranceportal.dto.customer.CustomerView;
import de.id.insuranceportal.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerView create(@Valid @RequestBody CustomerCreation dto) {
        return customerService.create(dto);
    }

    @GetMapping("/{id}")
    public CustomerView searchById(@PathVariable UUID id) {
        return customerService.getById(id);
    }

    @GetMapping
    public List<CustomerView> all() {
        return customerService.findAll();
    }

    @PutMapping("/{id}")
    public CustomerView update(@PathVariable UUID id, @Valid @ RequestBody CustomerUpdate dto) {
        return customerService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        customerService.delete(id);
    }
}
