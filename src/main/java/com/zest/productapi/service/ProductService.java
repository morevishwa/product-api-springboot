package com.zest.productapi.service;

import com.zest.productapi.entity.Item;
import com.zest.productapi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product getProductById(Integer id);

    Page<Product> getAllProducts(Pageable pageable);

    Product updateProduct(Integer id, Product product);

    void deleteProduct(Integer id);

    List<Item> getItemsByProductId(Integer productId);
}
