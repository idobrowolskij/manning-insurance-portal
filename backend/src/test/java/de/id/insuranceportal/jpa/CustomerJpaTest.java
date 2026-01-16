package de.id.insuranceportal.jpa;

import de.id.insuranceportal.config.JpaAuditingConfig;
import de.id.insuranceportal.entity.Customer;
import de.id.insuranceportal.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAuditingConfig.class)
@ActiveProfiles("test")
public class CustomerJpaTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveCustomer_shouldInitializeIdAndAuditFieldsAndLoadCorrectly() {
        Customer customer = new Customer();
        customer.setSurname("Mustermann");
        customer.setName("Max");
        customer.setEmail("max.mustermann@test.de");

        Customer saved = customerRepository.save(customer);

        assertNotNull(saved.getId(), "UUID should be generated automatically");
        assertNotNull(saved.getAuditTimeStamps().getCreated(), "created timestamp should be set");
        assertNotNull(saved.getAuditTimeStamps().getUpdated(), "updated timestamp should be set");

        Optional<Customer> loaded = customerRepository.findById(saved.getId());
        assertTrue(loaded.isPresent(), "Customer should be found in database");

        Customer loadedCustomer = loaded.get();

        assertEquals(saved.getId(), loadedCustomer.getId());
        assertEquals(saved.getEmail(), loadedCustomer.getEmail());
        assertEquals(saved.getAuditTimeStamps().getCreated(), loadedCustomer.getAuditTimeStamps().getCreated());
        assertEquals(saved.getAuditTimeStamps().getUpdated(), loadedCustomer.getAuditTimeStamps().getUpdated());
    }

}
