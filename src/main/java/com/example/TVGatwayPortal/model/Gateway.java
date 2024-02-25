package com.example.TVGatwayPortal.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.domain.EntityScan;



import java.util.Set;

@Entity
public class Gateway {
    @Id
    private String serialNumber; // Unique serial number
    private String name; // Human-readable name
    @Pattern(regexp = "^\\d+\\.\\d+\\.\\d+\\.\\d+$", message = "Invalid IPv4 address")
    private String ipAddress; // IPv4 address
    @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(max = 10, message = "No more than 10 peripheral devices allowed")
    private Set <PeripheralDevice> peripheralDevices; // Associated peripheral devices

    // Constructors, getters, and setters
}

