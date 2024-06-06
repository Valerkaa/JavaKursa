package com.example.dom.service;

import com.example.dom.dto.room.RequestRoom;
import com.example.dom.dto.room.ResponseRoom;
import com.example.dom.entity.RoomEntity;
import com.example.dom.exception.RoomNotFoundException;
import com.example.dom.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<ResponseRoom> getRooms() {
        List<RoomEntity> entities = roomRepository.findAll();
        return entities.stream()
                .map(ResponseRoom::toModel)
                .collect(Collectors.toList());
    }

    public ResponseRoom getRoom(Long id) {
        RoomEntity entity = roomRepository.findById(id).orElse(null);
        if (entity == null) throw new EntityNotFoundException("Room with id " + id + " not found");
        return ResponseRoom.toModel(entity);
    }

    public ResponseRoom createRoom(RequestRoom room) {
        RoomEntity entity = new RoomEntity();
        entity.setName(room.getName());
        entity.setUser(room.getUser());
        return ResponseRoom.toModel(roomRepository.save(entity));
    }

    public ResponseRoom updateRoom(Long id, RequestRoom roomDetails) {
        RoomEntity entity = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id " + id));

        entity.setName(roomDetails.getName());
        entity.setUser(roomDetails.getUser());
        return ResponseRoom.toModel(roomRepository.save(entity));
    }

    public Long deleteRoom(Long id) {
        try {
            roomRepository.deleteById(id);
            return id;
        } catch (Exception e) {
            throw new InternalError("Something went wrong");
        }
    }
}