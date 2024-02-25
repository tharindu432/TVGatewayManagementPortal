package com.example.TVGatwayPortal.controller;

import com.example.TVGatwayPortal.model.Gateway;
import com.example.TVGatwayPortal.model.PeripheralDevice;
import com.example.TVGatwayPortal.service.GatewayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gateways")
public class GatewayController {
    private final GatewayService gatewayService;

    @Autowired
    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping
    public ResponseEntity<List<Gateway>> getAllGateways() {
        List<Gateway> gateways = gatewayService.getAllGateways();
        return new ResponseEntity<>(gateways, HttpStatus.OK);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<Gateway> getGateway(@PathVariable String serialNumber) {
        Gateway gateway = gatewayService.getGateway(serialNumber);
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Gateway> addGateway(@RequestBody @Valid Gateway gateway) {
        Gateway addedGateway = gatewayService.addGateway(gateway);
        return new ResponseEntity<>(addedGateway, HttpStatus.CREATED);
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<Gateway> updateGateway(
            @PathVariable String serialNumber,
            @RequestBody @Valid Gateway updatedGateway) {
        Gateway gateway = gatewayService.updateGateway(serialNumber, updatedGateway);
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }

    @PatchMapping("/{serialNumber}")
    public ResponseEntity<Gateway> partialUpdateGateway(
            @PathVariable String serialNumber,
            @RequestBody Map<String, Object> updates) {
        Gateway gateway = gatewayService.partialUpdateGateway(serialNumber, updates);
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Void> deleteGateway(@PathVariable String serialNumber) {
        gatewayService.deleteGateway(serialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{serialNumber}/devices")
    public ResponseEntity<List<PeripheralDevice>> getPeripheralDevicesForGateway(@PathVariable String serialNumber) {
        List<PeripheralDevice> devices = gatewayService.getPeripheralDevicesForGateway(serialNumber);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    // Additional endpoints for managing peripheral devices within a gateway

    @PostMapping("/{serialNumber}/devices")
    public ResponseEntity<PeripheralDevice> addPeripheralDeviceToGateway(
            @PathVariable String serialNumber,
            @RequestBody @Valid PeripheralDevice device) {
        PeripheralDevice addedDevice = gatewayService.addPeripheralDeviceToGateway(serialNumber, device);
        return new ResponseEntity<>(addedDevice, HttpStatus.CREATED);
    }

    @DeleteMapping("/{serialNumber}/devices/{deviceUid}")
    public ResponseEntity<Void> removePeripheralDeviceFromGateway(
            @PathVariable String serialNumber,
            @PathVariable Long deviceUid) {
        gatewayService.removePeripheralDeviceFromGateway(serialNumber, deviceUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

