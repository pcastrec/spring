package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return this.productRepository.findAll().stream()
        .map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = this.productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product: " + id + " not found"));
        return convertToDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = this.productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product: " + id + " not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        Product updatedProduct = this.productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    public Boolean deleteProduct(Long id) {
        if(!this.productRepository.existsById(id)) {
            return false;
        }
        this.productRepository.deleteById(id);
        return true;
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return this.productRepository.findByCategoryId(categoryId)
        .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> searchByName(String name) {
        return this.productRepository.findByNameContainingIgnoreCase(name)
        .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
}
