package com.example.TVGatwayPortal.service;

import com.example.TVGatwayPortal.model.Gateway;
import com.example.TVGatwayPortal.model.PeripheralDevice;
import com.example.TVGatwayPortal.repository.GatewayRepository;
import com.example.TVGatwayPortal.service.PeripheralDeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class GatewayService {
    private final GatewayRepository gatewayRepository;
    private final PeripheralDeviceService peripheralDeviceService;

    @Autowired
    public GatewayService(GatewayRepository gatewayRepository, PeripheralDeviceService peripheralDeviceService) {
        this.gatewayRepository = gatewayRepository;
        this.peripheralDeviceService = peripheralDeviceService;
    }

    public List<Gateway> getAllGateways() {
        return gatewayRepository.findAll();
    }

    public Gateway getGateway(String serialNumber) {
        return gatewayRepository.findById(serialNumber)
                .orElseThrow(() -> new EntityNotFoundException("Gateway not found with serial number: " + serialNumber));
    }

    public Gateway addGateway(Gateway gateway) {
        validateGateway(gateway);
        return gatewayRepository.save(gateway);
    }

    public Gateway updateGateway(String serialNumber, Gateway updatedGateway) {
        Gateway existingGateway = getGateway(serialNumber);
        BeanUtils.copyProperties(updatedGateway, existingGateway, "serialNumber", "peripheralDevices");
        validateGateway(existingGateway);
        return gatewayRepository.save(existingGateway);
    }

    public Gateway partialUpdateGateway(String serialNumber, Map<String, Object> updates) {
        Gateway existingGateway = getGateway(serialNumber);
        ObjectMapper objectMapper = new ObjectMapper();
        Gateway patchedGateway = objectMapper.convertValue(updates, Gateway.class);
        BeanUtils.copyProperties(patchedGateway, existingGateway, getNullPropertyNames(patchedGateway));
        validateGateway(existingGateway);
        return gatewayRepository.save(existingGateway);
    }

    public void deleteGateway(String serialNumber) {
        Gateway existingGateway = getGateway(serialNumber);
        gatewayRepository.delete(existingGateway);
    }

    public List<PeripheralDevice> getPeripheralDevicesForGateway(String serialNumber) {
        Gateway gateway = getGateway(serialNumber);
        return new ArrayList<>(gateway.getPeripheralDevices());
    }

    public PeripheralDevice addPeripheralDeviceToGateway(String serialNumber, PeripheralDevice device) {
        Gateway gateway = getGateway(serialNumber);
        validatePeripheralDevice(device);
        device.setGateway(gateway);
        peripheralDeviceService.validatePeripheralDeviceCount(gateway);
        return peripheralDeviceService.addPeripheralDevice(device);
    }

    private void validatePeripheralDevice(PeripheralDevice device) {

    }

    public void removePeripheralDeviceFromGateway(String serialNumber, Long deviceUid) {
        peripheralDeviceService.deletePeripheralDevice(deviceUid);
    }

    private void validateGateway(Gateway gateway) {
        // Implement validation logic for Gateway fields
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
