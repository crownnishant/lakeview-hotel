package com.booking.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long bookingId;

    @Column(name="check_in")
    private LocalDate checkInDate;

    @Column(name="check_out")
    private LocalDate checkOutDate;

    @Column(name="guest_full_name")
    private String guestFullName;

    @Column(name="guest_email")
    private String guestEmail;

    @Column(name="adults")
    private int NumberOfAdults;

    @Column(name="children")
    private int numberOfChildren;

    @Column(name="total_guests")
    private int TotalNumberOfGuests;

    @Column(name = "confirmation_code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    /**
     * Calculates the total number of guests by summing the number of adults and children.
     * This method updates the TotalNumberOfGuests field with the calculated value.
     */

    public void calculateTotalNumberOfGuests(){
        this.TotalNumberOfGuests=this.NumberOfAdults+this.numberOfChildren;
    }

    // Setter methods that recalculate the total number of guests after setting the number of adults or children.

    public void setNumberOfAdults(int numberOfAdults) {
        NumberOfAdults = numberOfAdults;
        calculateTotalNumberOfGuests();
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
        calculateTotalNumberOfGuests();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }

}
