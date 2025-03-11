package com.booking.hotel.impl;

import com.booking.hotel.entities.Room;
import com.booking.hotel.exception.RoomNotFoundException;
import com.booking.hotel.repository.RoomRepository;
import com.booking.hotel.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {


    private final RoomRepository roomRepository;

    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal price) throws SQLException, IOException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setPrice(price);
/**
 * Checks if the provided MultipartFile is not empty.
 * If the file is not empty, it retrieves the file's bytes and converts them into a Blob.
 *
 * @param file the MultipartFile containing the photo
 * @throws IOException if an I/O error occurs reading the file
 * @throws SQLException if a database access error occurs
 */

        if(!file.isEmpty()) {
            byte [] photoBytes = file.getBytes();
            Blob photoBlob=new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }

        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room updateRoom(Long id, MultipartFile photo, String roomType, BigDecimal price) throws SQLException, IOException {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setRoomType(roomType);
            room.setPrice(price);
            if (!photo.isEmpty()) {
                byte[] photoBytes = photo.getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                room.setPhoto(photoBlob);
            }
            return roomRepository.save(room);
        } else {
            throw new IllegalArgumentException("Room with id " + id + " not found");
        }
    }
    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("room not found with given id"));
    }
}
