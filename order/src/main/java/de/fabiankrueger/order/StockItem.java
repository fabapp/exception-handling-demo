package de.fabiankrueger.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockItem {
    private String sku;
    private int qty;
}
