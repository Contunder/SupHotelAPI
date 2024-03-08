package com.supinfo.suphotel.hotel.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "open")
public class Open {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date", nullable = false)
    private Date date;

    public Open(Date date) {
        this.date = date;
    }
}
