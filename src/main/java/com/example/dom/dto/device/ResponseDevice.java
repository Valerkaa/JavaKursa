package com.example.dom.dto.device;

import com.example.dom.entity.DeviceEntity;
import lombok.Data;

@Data
public class ResponseDevice {
    private Long id;
    private String name;
    private String type;
    private Long roomId;

    public static ResponseDevice toModel(DeviceEntity entity) {
        ResponseDevice model = new ResponseDevice();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setType(entity.getType());
        model.setRoomId(entity.getRoom().getId());
        return model;
    }
}