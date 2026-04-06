package com.ecommerce.order_service.client;
import com.ecommerce.order_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "INVENTORY-SERVICE", configuration = FeignConfig.class)
public interface InventoryClient {

    @GetMapping("/api/inventory/{productId}")
    Boolean isInStock(@PathVariable Long productId);
}