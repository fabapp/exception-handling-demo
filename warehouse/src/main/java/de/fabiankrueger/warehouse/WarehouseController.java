package de.fabiankrueger.warehouse;

import brave.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping(path = "/delivery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Delivery> delivery(@RequestBody Delivery delivery) {
        return new ResponseEntity<>(warehouseService.processDelivery(delivery), HttpStatus.CREATED);
    }

    @PostMapping(path = "/stockItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockItem> createStockItem(@RequestBody StockItem stockItem) {
        return new ResponseEntity<>(warehouseService.createStockItem(stockItem), HttpStatus.CREATED);
    }

    @GetMapping(path = "/stockItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockItem>> getStock() {
        return ResponseEntity.ok(warehouseService.getStock());
    }

    @GetMapping(path = "/stockItem/{sku}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockItem> getStock(@PathVariable("sku") String sku) {
        return ResponseEntity.ok(warehouseService.getStockItem(sku));
    }

    @PostMapping(path = "/shipment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        return new ResponseEntity<>(warehouseService.createShipment(shipment), HttpStatus.CREATED);
    }
}
