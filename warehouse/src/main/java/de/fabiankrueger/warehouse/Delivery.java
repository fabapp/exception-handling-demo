package de.fabiankrueger.warehouse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;
    private String sku;
    private String name;
    private int qty;
}
