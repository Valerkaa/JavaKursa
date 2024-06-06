package com.example.dom.dto.room;

import com.example.dom.entity.RoomEntity;
import lombok.Data;

@Data
public class ResponseRoom {
    private Long id;
    private String name;
    private Long userId;

    public static ResponseRoom toModel(RoomEntity entity) {
        ResponseRoom model = new ResponseRoom();
        model.setId(entity.getId());
        model.setName(entity.getName());
        if (entity.getUser() != null) {
            model.setUserId(entity.getUser().getId());
        }
        return model;
    }
}