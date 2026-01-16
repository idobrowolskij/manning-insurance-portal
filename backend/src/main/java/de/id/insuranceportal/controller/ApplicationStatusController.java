package de.id.insuranceportal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class ApplicationStatusController {
    @Value("${api.version}")
    private String apiVersion;

    @GetMapping
    public String getStatus() {
        return this.apiVersion + " running";
    }
}
