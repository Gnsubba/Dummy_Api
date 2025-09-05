package com.example.dummy_api.service;

import com.example.dummy_api.exceptions.IdNotFoundException;
import com.example.dummy_api.models.Room;
import com.example.dummy_api.pojo.RoomPojo;

import java.util.List;

public interface RoomService {

    List<Room> getAll();

    Room save(RoomPojo roomPojo);

    Room update(RoomPojo roomPojo) throws IdNotFoundException;

    boolean delete(Long roomId) throws IdNotFoundException;

    boolean deleteAll();
}
