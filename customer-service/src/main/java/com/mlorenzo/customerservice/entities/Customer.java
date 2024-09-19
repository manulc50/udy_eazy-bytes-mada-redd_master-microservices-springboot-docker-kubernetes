package com.mlorenzo.customerservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String email;
    private String mobileNumber;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "customer")
    private Account account;

    public void addAccount(Account account) {
        this.setAccount(account);
        account.setCustomer(this);
    }

    /*@PrePersist
    void setCreatedAudit() {
        setCreatedAt(LocalDateTime.now());
        setCreatedBy("Anonymous");
    }*/
}
