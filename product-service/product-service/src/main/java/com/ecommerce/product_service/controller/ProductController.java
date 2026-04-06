package com.ecommerce.product_service.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Dummy in-memory list (we will replace with DB later)
    private List<String> products = new ArrayList<>();

    // GET all products
    @GetMapping
    public List<String> getAllProducts() {
        return products;
    }

    // GET product by ID (dummy logic)
    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        if (id < products.size()) {
            return products.get(id);
        } else {
            return "Product not found";
        }
    }

    // ADD product
    @PostMapping
    public String addProduct(@RequestBody String product) {
        products.add(product);
        return "Product added successfully";
    }

    // UPDATE product
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody String product) {
        if (id < products.size()) {
            products.set(id, product);
            return "Product updated successfully";
        } else {
            return "Product not found";
        }
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        if (id < products.size()) {
            products.remove(id);
            return "Product deleted successfully";
        } else {
            return "Product not found";
        }
    }
}