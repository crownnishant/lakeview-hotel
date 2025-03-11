package com.booking.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rooms")
@Getter
@Setter
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private String roomType;
    private BigDecimal price;
    private boolean isAvailable=false;

    @Lob
    private Blob photo;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    private List<BookedRoom> bookedRooms;

    public Room(){
        this.bookedRooms=new ArrayList<>();
    }

    public void AddBookings(BookedRoom booking){
        if(bookedRooms==null){
            bookedRooms= new ArrayList<>();

        }
        bookedRooms.add(booking);
        booking.setRoom(this);
        isAvailable=true;
        String bookingCode= RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode);

    }

}
