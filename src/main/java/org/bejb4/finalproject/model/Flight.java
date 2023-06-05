package org.bejb4.finalproject.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFlight;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jadwal", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Jadwal jadwal;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_maskapai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Maskapai maskapai;

    private int jmlKursiSeluruh;

    private int jmlKursiEkonomi;

    private int jmlKursiBisnis;

    private int hargaEkonomi;

    private int hargaBisnis;


}
