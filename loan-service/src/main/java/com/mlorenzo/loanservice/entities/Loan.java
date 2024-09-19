package com.mlorenzo.loanservice.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    private Long number;

    private String type;
    private BigDecimal total;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
    private String customerMobileNumber;

    @Embedded
    // No creamos los métodos getter y setter para esta propiedad
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    // Nota: Tenemos que crear una instancia de esta clase embebida o interna para que funcionen correctamente las
    // anotaciones de auditoría @CreatedDate, @CreatedBy, etc... Pero no es necesario para añadir aquí su contenido.
    private Audit audit = new Audit();
}
