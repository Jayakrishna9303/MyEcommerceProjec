package com.example.ecommerce;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
    @CrossOrigin(origins = "*")

public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    // 1️⃣ GET all products
    @GetMapping
    public List<Product> all() {
        return repo.findAll();
    }

    // 2️⃣ GET product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3️⃣ POST new product
    @PostMapping
    public Product add(@RequestBody Product product) {
        return repo.save(product);
    }

    // 4️⃣ UPDATE product
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        return repo.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setDescription(((Product) updatedProduct).getDescription());
                    return ResponseEntity.ok(repo.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5️⃣ DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
