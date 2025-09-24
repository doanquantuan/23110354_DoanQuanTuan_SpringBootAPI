package vn.iotstar.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.ProductEntity;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
    private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<ProductEntity> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<ProductEntity> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<ProductEntity> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public Optional<ProductEntity> findByProductName(String name) {
		return productRepository.findByProductName(name);
	}

	@Override
	public ProductEntity save(ProductEntity product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(ProductEntity entity) {
		productRepository.delete(entity);
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public List<ProductEntity> findByProductNameContaining(String name) {
		return productRepository.findByProductNameContaining(name);
	}

	@Override
	public Page<ProductEntity> findByProductNameContaining(String name, Pageable pageable) {
		return productRepository.findByProductNameContaining(name, pageable);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public Optional<ProductEntity> findByCreateDate(Date createDate) {
		return productRepository.findByCreateDate(createDate);
	}
	
	

}
