package de.fabiankrueger.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "warehouse", url="${warehouse.url}")
public interface WarehouseClient {
    @RequestMapping(method = RequestMethod.GET, value = "/stockItem/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    StockItem getStockItem(@PathVariable("sku") String sku);

    @RequestMapping(method = RequestMethod.POST, value = "/shipment")
    Order ship(Order order);
}
