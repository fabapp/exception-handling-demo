package de.fabiankrueger.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Order {
    private String sku;
    private int qty;
}
