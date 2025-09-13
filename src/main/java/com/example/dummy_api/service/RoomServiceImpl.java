package com.example.dummy_api.service;

import com.example.dummy_api.exceptions.IdNotFoundException;
import com.example.dummy_api.models.Room;
import com.example.dummy_api.models.RoomProperty;
import com.example.dummy_api.pojo.RoomPojo;
import com.example.dummy_api.repository.RoomPropertyRepository;
import com.example.dummy_api.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomPropertyRepository roomPropertyRepository;
    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(RoomPojo roomPojo) {
        Room newRoom = new Room();


        newRoom.setNumOfRooms(roomPojo.getNumOfRooms());
        System.out.println("[RoomService] - The value of roomPojo.numOfRooms is: " + roomPojo.getNumOfRooms());
        System.out.println("[RoomService] - The value of newRoom.numOfRooms is: " + newRoom.getNumOfRooms());

        newRoom.setLocation(roomPojo.getLocation());
        newRoom.setPrice(roomPojo.getPrice());
        newRoom = roomRepository.save(newRoom);

        List<RoomProperty> roomProperties = new ArrayList<>();
        for (Map<String, String> property: roomPojo.getRoomProperties()){
            RoomProperty property1 = new RoomProperty();
            property1.setRoom(newRoom);
            property1.setName(property.get("name"));
            property1.setValue(property.get("property"));
            property1 = roomPropertyRepository.save(property1);
            roomProperties.add(property1);
        }
        newRoom.setPropertyList(roomProperties);

        return newRoom;
    }

    @Override
    public Room update(RoomPojo roomPojo) throws IdNotFoundException {
        if(roomPojo.getRoomId()==null){
            throw new IdNotFoundException("roomId shouldn't be empty");
        }
        Room room = new Room();
        room.setRoomId(roomPojo.getRoomId());
        room.setNumOfRooms(roomPojo.getNumOfRooms());
        room.setLocation(roomPojo.getLocation());
        room.setPrice(roomPojo.getPrice());
        return roomRepository.save(room);
    }

    @Override
    public boolean delete(Long roomId) throws IdNotFoundException {

        roomRepository.deleteById(roomId);
        return roomRepository.findById(roomId).isEmpty();
    }

    @Override
    public boolean deleteAll() {
        roomRepository.deleteAll();
        return roomRepository.findAll().isEmpty();
    }
}
