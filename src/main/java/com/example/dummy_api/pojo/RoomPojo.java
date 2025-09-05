package com.example.dummy_api.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPojo {

    private Long roomId;
    @NotBlank
    private String location;

    @NotBlank
    private Double price;

    @NotBlank
    private  Integer numOfRooms;
}
