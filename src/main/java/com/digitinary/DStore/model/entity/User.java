package com.digitinary.DStore.model.entity;

import com.digitinary.DStore.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Basic(optional = false)
    private String email;

    @Basic(optional = false)
    private String password;

    private Integer age;

    @CreationTimestamp
    @Column(name = "registered_date", nullable = false, updatable = false)
    private Timestamp registeredDate;

    @UpdateTimestamp
    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // If a user is deleted, his cart should be deleted -> items in the cart are also deleted.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    // If a user is deleted, his previous orders should be kept for historical needs.
    // TODO: iterate over the orders and set user_id to NULL. to Detach the orders from the user.
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    // If a user is deleted, his address/es should be deleted automatically.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    // Proper helper method to avoid infinite loops
    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this); // Maintain bidirectional consistency
    }
}
