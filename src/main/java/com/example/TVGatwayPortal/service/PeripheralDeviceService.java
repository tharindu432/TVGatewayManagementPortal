package com.example.TVGatwayPortal.service;

import com.example.TVGatwayPortal.repository.PeripheralDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeripheralDeviceService {
    private final PeripheralDeviceRepository peripheralDeviceRepository;

    @Autowired
    public PeripheralDeviceService(PeripheralDeviceRepository peripheralDeviceRepository) {
        this.peripheralDeviceRepository = peripheralDeviceRepository;
    }

    // Service methods for CRUD operations
}

