package com.example.dom.controller;

import com.example.dom.dto.device.RequestDevice;
import com.example.dom.dto.device.ResponseDevice;
import com.example.dom.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ResponseDevice>> getDevices() {
        List<ResponseDevice> devices = deviceService.getDevices();
        return ResponseEntity.ok(devices);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDevice> getDevice(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getDevice(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDevice> createDevice(@Valid @RequestBody RequestDevice device) {
        ResponseDevice createdDevice = deviceService.createDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDevice);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDevice> updateDevice(@PathVariable Long id, @Valid @RequestBody RequestDevice deviceDetails) {
        ResponseDevice updatedDevice = deviceService.updateDevice(id, deviceDetails);
        return ResponseEntity.ok(updatedDevice);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteDevice(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.deleteDevice(id));
    }
}