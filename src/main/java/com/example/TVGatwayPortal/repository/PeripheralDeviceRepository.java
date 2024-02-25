package com.example.TVGatwayPortal.repository;

import com.example.TVGatwayPortal.model.PeripheralDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeripheralDeviceRepository extends JpaRepository<PeripheralDevice, Long> {
    List<PeripheralDevice> findByGateway_SerialNumber(String gatewaySerialNumber);

    Optional<Object> findByGateway_SerialNumberAndUid(String gatewaySerialNumber, Long deviceUid);
}

