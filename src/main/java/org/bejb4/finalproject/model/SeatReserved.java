package org.bejb4.finalproject.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "seat_reserved")
public class SeatReserved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSeatReserved;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_seat", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Seat seat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jadwal", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Jadwal jadwal;

}
