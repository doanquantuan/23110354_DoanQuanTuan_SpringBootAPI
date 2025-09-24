package vn.iotstar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer >{
	// Tìm kiếm theo tên
    List<CategoryEntity> findByCategoryNameContaining(String name);

    // Tìm kiếm có phân trang
    Page<CategoryEntity> findByCategoryNameContaining(String name, Pageable pageable);
    
    Optional<CategoryEntity> findByCategoryName(String name);
	
}
