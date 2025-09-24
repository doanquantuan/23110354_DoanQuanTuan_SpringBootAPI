package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iotstar.entity.CategoryEntity;

public interface ICategoryService {
	
	Page<CategoryEntity> findAll(Pageable pageable);
	
    List<CategoryEntity> findAll();
    
    Optional<CategoryEntity> findById(Integer id);
    
    Optional<CategoryEntity> findByCategoryName(String name);
    
    CategoryEntity save(CategoryEntity category);
    
    void delete(CategoryEntity entity);
    
    void deleteById(Integer id);
    
    List<CategoryEntity> findByCategoryNameContaining(String name);
    
    Page<CategoryEntity> findByCategoryNameContaining(String name, Pageable pageable);

	long count();
}
