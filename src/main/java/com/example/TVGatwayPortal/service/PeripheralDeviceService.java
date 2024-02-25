package com.example.TVGatwayPortal.service;

import com.example.TVGatwayPortal.model.Gateway;
import com.example.TVGatwayPortal.model.PeripheralDevice;
import com.example.TVGatwayPortal.repository.PeripheralDeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PeripheralDeviceService {
    private final PeripheralDeviceRepository peripheralDeviceRepository;

    @Autowired
    public PeripheralDeviceService(PeripheralDeviceRepository peripheralDeviceRepository) {
        this.peripheralDeviceRepository = peripheralDeviceRepository;
    }

    public List<PeripheralDevice> getAllDevicesForGateway(String gatewaySerialNumber) {
        return peripheralDeviceRepository.findByGateway_SerialNumber(gatewaySerialNumber);
    }

    public PeripheralDevice getDevice(String gatewaySerialNumber, Long deviceUid) {
        return (PeripheralDevice) peripheralDeviceRepository.findByGateway_SerialNumberAndUid(gatewaySerialNumber, deviceUid)
                .orElseThrow(() -> new EntityNotFoundException("Peripheral Device not found with UID: " + deviceUid));
    }

    public PeripheralDevice addDevice(String gatewaySerialNumber, PeripheralDevice device) {
        validatePeripheralDevice(device);
        device.setGateway(new Gateway(gatewaySerialNumber));
        return peripheralDeviceRepository.save(device);
    }

    public PeripheralDevice updateDevice(String gatewaySerialNumber, Long deviceUid, PeripheralDevice updatedDevice) {
        PeripheralDevice existingDevice = getDevice(gatewaySerialNumber, deviceUid);
        BeanUtils.copyProperties(updatedDevice, existingDevice, "uid", "gateway");
        validatePeripheralDevice(existingDevice);
        return peripheralDeviceRepository.save(existingDevice);
    }

    public PeripheralDevice partialUpdateDevice(String gatewaySerialNumber, Long deviceUid, Map<String, Object> updates) {
        PeripheralDevice existingDevice = getDevice(gatewaySerialNumber, deviceUid);
        ObjectMapper objectMapper = new ObjectMapper();
        PeripheralDevice patchedDevice = objectMapper.convertValue(updates, PeripheralDevice.class);
        BeanUtils.copyProperties(patchedDevice, existingDevice, getNullPropertyNames(patchedDevice));
        validatePeripheralDevice(existingDevice);
        return peripheralDeviceRepository.save(existingDevice);
    }

    public void deleteDevice(String gatewaySerialNumber, Long deviceUid) {
        PeripheralDevice existingDevice = getDevice(gatewaySerialNumber, deviceUid);
        peripheralDeviceRepository.delete(existingDevice);
    }

    public void validatePeripheralDeviceCount(Gateway gateway) {
        if (gateway.getPeripheralDevices().size() >= 10) {
            throw new RuntimeException("Cannot add more than 10 peripheral devices to a gateway.");
        }
    }

    private void validatePeripheralDevice(PeripheralDevice device) {
        // Implement validation logic for PeripheralDevice fields
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

    public void deletePeripheralDevice(Long deviceUid) {
    }

    public PeripheralDevice addPeripheralDevice(PeripheralDevice device) {
        return device;
    }
}


