package vn.iotstar.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.CategoryEntity;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
    private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}


    @Override
    public Page<CategoryEntity> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    
    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryEntity> findByCategoryNameContaining(String name) {
        return categoryRepository.findByCategoryNameContaining(name);
    }

    @Override
    public Page<CategoryEntity> findByCategoryNameContaining(String name, Pageable pageable) {
        return categoryRepository.findByCategoryNameContaining(name, pageable);
    }


	@Override
	public long count() {
		return categoryRepository.count();
	}


	@Override
	public Optional<CategoryEntity> findByCategoryName(String name) {
		return categoryRepository.findByCategoryName(name);
	}


	@Override
	public void delete(CategoryEntity entity) {
		categoryRepository.delete(entity);
	}

}
