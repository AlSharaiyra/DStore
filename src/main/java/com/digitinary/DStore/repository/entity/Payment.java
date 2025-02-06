package com.digitinary.DStore.repository.entity;

import com.digitinary.DStore.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private double amount;

    @Basic(optional = false)
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Value("false")
    @Column(name = "is_payed")
    private boolean isPayed;

//    @JsonIgnore
//    @OneToOne(mappedBy = "payment")
//    private Order order;
}
