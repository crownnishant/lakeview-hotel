package com.booking.hotel.controller;

import com.booking.hotel.entities.Room;
import com.booking.hotel.response.RoomResponse;
import com.booking.hotel.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final IRoomService roomService;

    @PostMapping("/add/new-room")
    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("price") BigDecimal price) throws SQLException, IOException {

       Room savedRoom= roomService.addNewRoom(photo, roomType, price);
       RoomResponse response=new RoomResponse(savedRoom.getId(),savedRoom.getRoomType(),savedRoom.getPrice().toString());
         return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable Long id,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("price") BigDecimal price) throws SQLException, IOException {

        Room updatedRoom = roomService.updateRoom(id, photo, roomType, price);
        RoomResponse response = new RoomResponse(updatedRoom.getId(), updatedRoom.getRoomType(), updatedRoom.getPrice().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        RoomResponse response = new RoomResponse(room.getId(), room.getRoomType(), room.getPrice().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponse> response = rooms.stream()
                .map(room -> new RoomResponse(room.getId(), room.getRoomType(), room.getPrice().toString()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}
