package org.bejb4.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "jadwal")
public class Jadwal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJadwal;

    private long noPenerbangan;
    private LocalDate tglKeberangkatan;

    private LocalTime jamKeberangkatan;

    private LocalTime jamKedatangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_city_keberangkatan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City kotaKeberangkatan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_city_kedatangan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City kotaKedatangan;
}
