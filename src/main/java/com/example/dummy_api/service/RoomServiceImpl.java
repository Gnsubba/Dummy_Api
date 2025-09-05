package com.example.dummy_api.service;

import com.example.dummy_api.exceptions.IdNotFoundException;
import com.example.dummy_api.models.Room;
import com.example.dummy_api.pojo.RoomPojo;
import com.example.dummy_api.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

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
        return roomRepository.save(newRoom);
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
