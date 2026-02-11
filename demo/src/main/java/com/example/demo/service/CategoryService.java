package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return this.categoryRepository.findAll().stream()
        .map(this::convertToDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category: " + id + " not found"));
        return convertToDTO(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = this.categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product: " + id + " not found"));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category updatedCategory = this.categoryRepository.save(category);
        return convertToDTO(updatedCategory);
    }

    public Boolean deleteCategory(Long id) {
        if(!this.categoryRepository.existsById(id)) {
            return false;
        }
        this.categoryRepository.deleteById(id);
        return true;
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(category, dto);
        return dto;
    }

    private Category convertToEntity(CategoryDTO dto) {
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        return category;
    }
}
