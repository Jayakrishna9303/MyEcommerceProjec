
package com.example.ecommerce;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return repo.save(product);
    }

    @GetMapping
    public List<Product> all() {
        return repo.findAll();
    }
}
