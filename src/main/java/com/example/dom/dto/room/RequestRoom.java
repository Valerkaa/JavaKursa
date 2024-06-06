package com.example.dom.dto.room;

import com.example.dom.entity.UserEntity;
import lombok.Data;

@Data
public class RequestRoom {
    private String name;
    private UserEntity user;
}