package com.supinfo.suphotel.booking.model;

import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import com.supinfo.suphotel.user.infrastructure.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "get_in", nullable = false)
    private Date getIn;
    @Column(name = "get_out", nullable = false)
    private Date getOut;
    @Column(name = "people", nullable = false)
    private int people;
    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
