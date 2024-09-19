package com.mlorenzo.loanservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class Audit {

    @CreatedDate
    // Para que esta propiedad no aparezca en las sentencias SQL de tipo Update generadas por el proveedor de
    // persistencia(En este caso, Hibernate).
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    // Para que esta propiedad no aparezca en las sentencias SQL de tipo Insert generadas por el proveedor de
    // persistencia(En este caso, Hibernate).
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
