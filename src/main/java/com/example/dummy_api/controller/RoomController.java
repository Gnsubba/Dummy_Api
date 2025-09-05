package com.example.dummy_api.controller;

import com.example.dummy_api.exceptions.IdNotFoundException;
import com.example.dummy_api.models.Room;
import com.example.dummy_api.pojo.RoomPojo;
import com.example.dummy_api.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<Room> roomList = roomService.getAll();

        return ResponseEntity.ok(roomList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoomPojo roomPojo){
        Room room = roomService.save(roomPojo);

        return ResponseEntity.ok(room);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RoomPojo roomPojo) throws IdNotFoundException {
        Room room = roomService.update(roomPojo);

        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long roomId) throws IdNotFoundException {
        boolean isDeleted = roomService.delete(roomId);
        Map<String, Object> res = new HashMap<>();
        if(isDeleted){
            res.put("msg", "deleted");

        }
        else{
            res.put("msg", "not deleted");

        }
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() throws IdNotFoundException {
        boolean isDeleted = roomService.deleteAll();
        Map<String, Object> res = new HashMap<>();
        if(isDeleted){
            res.put("msg", "deleted");

        }
        else{
            res.put("msg", "not deleted");

        }
        return ResponseEntity.ok(res);
    }


}
