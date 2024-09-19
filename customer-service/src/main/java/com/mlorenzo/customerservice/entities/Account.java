package com.mlorenzo.customerservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Id
    private Long number;

    private String type;
    private String branchAddress;

    @OneToOne
    private Customer customer;

    /*@PrePersist
    void setCreatedAudit() {
        setCreatedAt(LocalDateTime.now());
        setCreatedBy("Anonymous");
    }*/
}
