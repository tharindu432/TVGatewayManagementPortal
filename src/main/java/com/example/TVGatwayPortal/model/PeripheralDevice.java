package com.example.TVGatwayPortal.model;

import jakarta.persistence.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.LocalDateTime;

@Entity
@Table(name = "peripheral_device")
public class PeripheralDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid; // Auto-generated UID
    private String vendor; // Vendor
    private LocalDateTime dateCreated; // Date created
    private boolean status; // Online/Offline
    @ManyToOne
    @JoinColumn(name = "gateway_serial_number")
    private Gateway gateway; // Associated gateway

    public PeripheralDevice(Long uid, String vendor, LocalDateTime dateCreated, boolean status, Gateway gateway) {
        this.uid = uid;
        this.vendor = vendor;
        this.dateCreated = dateCreated;
        this.status = status;
        this.gateway = gateway;
    }

    public PeripheralDevice() {

    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
}
