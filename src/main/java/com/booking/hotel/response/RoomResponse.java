package com.booking.hotel.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomResponse {
    private Long id;
    private String roomType;
    private String price;
    private boolean isAvailable;
    private String photo;
    private List<BookingResponse> bookings;

    public RoomResponse(Long id, String roomType, String price) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
    }

    public RoomResponse(Long id, String roomType, String price, boolean isAvailable, byte[] photoBytes, List<BookingResponse> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
        this.photo = photoBytes != null ? Base64.getEncoder().encodeToString(photoBytes) : null;
        this.bookings = bookings;
    }
}
