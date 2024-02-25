package com.example.TVGatwayPortal.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


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

    public Gateway(String serialNumber) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.ipAddress = ipAddress;
        this.peripheralDevices = peripheralDevices;
    }

    public Gateway() {

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Set<PeripheralDevice> getPeripheralDevices() {
        return peripheralDevices;
    }

    public void setPeripheralDevices(Set<PeripheralDevice> peripheralDevices) {
        this.peripheralDevices = peripheralDevices;
    }
}

