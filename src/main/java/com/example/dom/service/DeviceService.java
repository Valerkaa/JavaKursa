package com.example.dom.service;

import com.example.dom.dto.device.RequestDevice;
import com.example.dom.dto.device.ResponseDevice;
import com.example.dom.entity.DeviceEntity;
import com.example.dom.entity.RoomEntity;
import com.example.dom.exception.DeviceAlreadyExistException;
import com.example.dom.repository.DeviceRepository;
import com.example.dom.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<ResponseDevice> getDevices() {
        List<DeviceEntity> entities = deviceRepository.findAll();
        return entities.stream()
                .map(ResponseDevice::toModel)
                .collect(Collectors.toList());
    }

    public ResponseDevice getDevice(Long id) {
        DeviceEntity entity = deviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Device with id " + id + " not found"));
        return ResponseDevice.toModel(entity);
    }

    public ResponseDevice createDevice(RequestDevice requestDevice) {
        DeviceEntity entity = new DeviceEntity();
        entity.setName(requestDevice.getName());
        entity.setType(requestDevice.getType());

        RoomEntity room = roomRepository.findById(requestDevice.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + requestDevice.getRoomId() + " not found"));
        entity.setRoom(room);

        return ResponseDevice.toModel(deviceRepository.save(entity));
    }

    public ResponseDevice updateDevice(Long id, RequestDevice requestDevice) {
        DeviceEntity entity = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device with id " + id + " not found"));

        entity.setName(requestDevice.getName());
        entity.setType(requestDevice.getType());

        RoomEntity room = roomRepository.findById(requestDevice.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + requestDevice.getRoomId() + " not found"));
        entity.setRoom(room);

        return ResponseDevice.toModel(deviceRepository.save(entity));
    }

    public Long deleteDevice(Long id) {
        deviceRepository.deleteById(id);
        return id;
    }
}