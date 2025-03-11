package com.booking.hotel.service;

import com.booking.hotel.entities.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IRoomService {

    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal price) throws SQLException, IOException;

    List<Room> getAllRooms();

    Room updateRoom(Long id, MultipartFile photo, String roomType, BigDecimal price) throws SQLException, IOException;

    Room getRoomById(Long id);

}
