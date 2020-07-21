package de.fabiankrueger.warehouse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final StockItemRepository stockItemRepository;
    private final DeliveryRepository deliveryRepository;

    public StockItem getStockItem(String sku) {
        final Optional<StockItem> optionalStockItem = stockItemRepository.findBySku(sku);
        return optionalStockItem.orElseThrow(() -> new ItemNotInStockException("StockItem with sku '"+sku+"' does not exist."));
    }

    public StockItem createStockItem(StockItem stockItem) {
        log.info("Creating stockItem: " + stockItem.getSku());
        return stockItemRepository.save(stockItem);
    }

    public List<StockItem> getStock() {
        return stockItemRepository.findAll();
    }

    public Shipment createShipment(Shipment shipment) {
        log.info("Create shipment: " + shipment);
        StockItem stockItem = getStockItem(shipment.getSku());
        int currentQty = stockItem.getQty();
        int orderedQty = shipment.getQty();
        int newQty = currentQty - orderedQty;
        if(newQty < 0) {
            throw new InsufficientStockException("Item '"+shipment.getSku()+"' has insufficient qty, ordered: '"+shipment.getQty()+"' available: '"+shipment.getQty()+"'");
        }
        stockItem.setQty(newQty);
        return shipment;
    }

    @Transactional
    public Delivery processDelivery(Delivery delivery) {
        final StockItem stockItem = getStockItem(delivery.getSku());
        int currentQty = stockItem.getQty();
        int deliveredQty = delivery.getQty();
        int newQty = currentQty + deliveredQty;
        stockItem.setQty(newQty);
        return deliveryRepository.save(delivery);
    }
}
