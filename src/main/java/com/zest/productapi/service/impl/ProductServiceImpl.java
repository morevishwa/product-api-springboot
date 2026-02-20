package com.zest.productapi.service.impl;

import com.zest.productapi.entity.Item;
import com.zest.productapi.entity.Product;
import com.zest.productapi.exception.ResourceNotFoundException;
import com.zest.productapi.repository.ItemRepository;
import com.zest.productapi.repository.ProductRepository;
import com.zest.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    public Product createProduct(Product product) {

        product.setCreatedOn(LocalDateTime.now());


        if (product.getItems() != null) {
            product.getItems().forEach(item ->
                    item.setProduct(product)
            );
        }

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProduct(Integer id, Product updatedProduct) {

        Product existing = getProductById(id);

        existing.setProductName(updatedProduct.getProductName());
        existing.setModifiedBy(updatedProduct.getModifiedBy());
        existing.setModifiedOn(LocalDateTime.now());

        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Item> getItemsByProductId(Integer productId) {
        getProductById(productId); // ensures product exists
        return itemRepository.findByProductId(productId);
    }
}