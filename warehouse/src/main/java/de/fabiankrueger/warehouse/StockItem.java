package de.fabiankrueger.warehouse;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class StockItem {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String sku;
    private String name;
    private int qty;
}
