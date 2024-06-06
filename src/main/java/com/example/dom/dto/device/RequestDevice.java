package com.example.dom.dto.device;

import lombok.Data;

@Data
public class RequestDevice {
    private String name;
    private String type;
    private Long roomId;
}