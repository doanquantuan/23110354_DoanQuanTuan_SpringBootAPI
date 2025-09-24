package vn.iotstar.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iotstar.entity.ProductEntity;

public interface IProductService {

	Page<ProductEntity> findAll(Pageable pageable);
	
    List<ProductEntity> findAll();
    
    Optional<ProductEntity> findById(Integer id);
    
    Optional<ProductEntity> findByProductName(String name);
    
    ProductEntity save(ProductEntity product);
    
    void delete(ProductEntity entity);
    
    void deleteById(Integer id);
    
    List<ProductEntity> findByProductNameContaining(String name);
    
    Page<ProductEntity> findByProductNameContaining(String name, Pageable pageable);

	long count();
	
	public Optional<ProductEntity> findByCreateDate(Date createDate);
}
