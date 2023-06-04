package org.bejb4.finalproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSeat;

    private int seatNumber;

    private String seatClass;

}
