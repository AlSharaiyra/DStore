package com.digitinary.DStore.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String title;

    @Basic(optional = false)
    private String country;

    @Basic(optional = false)
    private String city;

    @Basic(optional = false)
    private String street;

    @Basic(optional = false)
    @Column(name = "building_number")
    private Integer buildingNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
