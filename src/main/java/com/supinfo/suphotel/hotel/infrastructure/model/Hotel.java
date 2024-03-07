package com.supinfo.suphotel.hotel.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "pictures")
    private String pictures;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_openin",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "open_id", referencedColumnName = "id")
    )
    private Open openIn;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_openout",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "open_id", referencedColumnName = "id")
    )
    private Open openOut;

}
