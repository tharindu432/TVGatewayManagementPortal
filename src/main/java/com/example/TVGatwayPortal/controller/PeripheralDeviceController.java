package com.example.TVGatwayPortal.controller;

import com.example.TVGatwayPortal.model.PeripheralDevice;
import com.example.TVGatwayPortal.service.GatewayService;
import com.example.TVGatwayPortal.service.PeripheralDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gateways/{gatewaySerialNumber}/devices")
public class PeripheralDeviceController {
    private final PeripheralDeviceService peripheralDeviceService;

    @Autowired
    public PeripheralDeviceController(PeripheralDeviceService peripheralDeviceService) {
        this.peripheralDeviceService = peripheralDeviceService;
    }

    @GetMapping
    public ResponseEntity<List<PeripheralDevice>> getAllPeripheralDevices(@PathVariable String gatewaySerialNumber) {
        // Implement logic to retrieve all peripheral devices for a given gateway
        List<PeripheralDevice> devices = peripheralDeviceService.getAllDevicesForGateway(gatewaySerialNumber);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/{deviceUid}")
    public ResponseEntity<PeripheralDevice> getPeripheralDevice(
            @PathVariable String gatewaySerialNumber,
            @PathVariable Long deviceUid) {
        // Implement logic to retrieve a specific peripheral device for a given gateway
        PeripheralDevice device = peripheralDeviceService.getDevice(gatewaySerialNumber, deviceUid);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PeripheralDevice> addPeripheralDevice(
            @PathVariable String gatewaySerialNumber,
            @RequestBody PeripheralDevice device) {
        // Implement logic to add a new peripheral device for a given gateway
        PeripheralDevice addedDevice = peripheralDeviceService.addDevice(gatewaySerialNumber, device);
        return new ResponseEntity<>(addedDevice, HttpStatus.CREATED);
    }

    @PutMapping("/{deviceUid}")
    public ResponseEntity<PeripheralDevice> updatePeripheralDevice(
            @PathVariable String gatewaySerialNumber,
            @PathVariable Long deviceUid,
            @RequestBody PeripheralDevice updatedDevice) {
        // Implement logic to update a specific peripheral device for a given gateway
        PeripheralDevice device = peripheralDeviceService.updateDevice(gatewaySerialNumber, deviceUid, updatedDevice);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @PatchMapping("/{deviceUid}")
    public ResponseEntity<PeripheralDevice> partialUpdatePeripheralDevice(
            @PathVariable String gatewaySerialNumber,
            @PathVariable Long deviceUid,
            @RequestBody Map<String, Object> updates) {
        // Implement logic to perform partial update on a specific peripheral device for a given gateway
        PeripheralDevice device = peripheralDeviceService.partialUpdateDevice(gatewaySerialNumber, deviceUid, updates);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @DeleteMapping("/{deviceUid}")
    public ResponseEntity<Void> deletePeripheralDevice(
            @PathVariable String gatewaySerialNumber,
            @PathVariable Long deviceUid) {
        // Implement logic to delete a specific peripheral device for a given gateway
        peripheralDeviceService.deleteDevice(gatewaySerialNumber, deviceUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

