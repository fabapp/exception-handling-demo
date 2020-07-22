package de.fabiankrueger.warehouse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Shipment {
    private String sku;
    private int qty;
}
