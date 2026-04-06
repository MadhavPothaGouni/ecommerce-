package com.ecommerce.inventory_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @GetMapping("/{productId}")
    public Boolean isInStock(@PathVariable Long productId) {
        return true;
    }
}