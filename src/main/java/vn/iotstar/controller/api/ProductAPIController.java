package vn.iotstar.controller.api;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.entity.CategoryEntity;
import vn.iotstar.entity.ProductEntity;
import vn.iotstar.model.ProductModel;
import vn.iotstar.model.Response;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.IStorageService;

@RestController
@RequestMapping(path = "/api/product")
public class ProductAPIController {
	
	@Autowired
	IProductService productService;
	
	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	IStorageService storageService;
	
	@GetMapping
	public ResponseEntity<?> getAllProduct() {
		return new ResponseEntity<Response>(new Response(true, "Thành công", productService.findAll()), HttpStatus.OK);
	}
	
	@PostMapping(path = "/addProduct")
	public ResponseEntity<?> saveOrUpdate(@Validated @RequestParam("productName") String productName,
			@RequestParam("imageFile") MultipartFile productImages,
			//@RequestParam("images") String images,
			@Validated @RequestParam("unitPrice") Double productPrice,
			@Validated @RequestParam("discount") Double promotionalPrice,
			@Validated @RequestParam("description") String productDescription,
			@Validated @RequestParam("categoryId") Integer categoryId,
			@Validated @RequestParam("quantity") Integer quantity,
			@Validated @RequestParam("status") Short status) {
		Optional<ProductEntity> optProduct = productService.findByProductName(productName);
		if (optProduct.isPresent()) {
			// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sản phẩm này đã tồn tại trong hệ thống");
			return new ResponseEntity<Response>(new Response(false, "Sản phẩm này đã tồn tại trong hệ thống", optProduct.get()), HttpStatus.BAD_REQUEST);
		} else {
			ProductEntity product = new ProductEntity();
			Timestamp timestamp = new Timestamp(new Date(System.currentTimeMillis()).getTime());
			try {
				ProductModel proModel = new ProductModel();
				//copy từ Model sang Entity
				BeanUtils.copyProperties(proModel, product);
				//xử lý category liên quan product
				CategoryEntity cateEntity = new CategoryEntity();
				
				cateEntity.setCategoryId(categoryId);
				product.setCategory(cateEntity);
				//kiểm tra tồn tại file, lưu file
				if(!proModel.getImageFile().isEmpty()) {
					UUID uuid = UUID.randomUUID();
					String uuString = uuid.toString();
					//lưu file vào trường Images
					product.setImages(storageService.getSorageFilename(proModel.getImageFile(), uuString));
					storageService.store(proModel.getImageFile(), product.getImages());
				}
				product.setCreateDate(timestamp);
				productService.save(product);
				optProduct = productService.findByCreateDate(timestamp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// return ResponseEntity.ok().body(product);
			return new ResponseEntity<Response>(new Response(true, "Thành công", optProduct.get()),HttpStatus.OK);
		}
	}
	
	@PutMapping(path = "/updateProduct")
	public ResponseEntity<?> updateProduct(
	        @Validated @RequestParam("productId") Integer productId,
	        @Validated @RequestParam("productName") String productName,
	        @RequestParam(value = "imageFile", required = false) MultipartFile productImages,
	        @Validated @RequestParam("unitPrice") Double productPrice,
	        @Validated @RequestParam("discount") Double promotionalPrice,
	        @Validated @RequestParam("description") String productDescription,
	        @Validated @RequestParam("categoryId") Integer categoryId,
	        @Validated @RequestParam("quantity") Integer quantity,
	        @Validated @RequestParam("status") Short status) {

	    Optional<ProductEntity> optProduct = productService.findById(productId);
	    if (!optProduct.isPresent()) {
	        return new ResponseEntity<Response>(new Response(false, "Sản phẩm không tồn tại", null), HttpStatus.NOT_FOUND);
	    }

	    try {
	        ProductEntity product = optProduct.get();
	        // cập nhật thông tin
	        product.setProductName(productName);
	        product.setUnitPrice(productPrice);
	        product.setDiscount(promotionalPrice);
	        product.setDescription(productDescription);
	        product.setQuantity(quantity);
	        product.setStatus(status);

	        // Cập nhật category
	        CategoryEntity cateEntity = new CategoryEntity();
	        cateEntity.setCategoryId(categoryId);
	        product.setCategory(cateEntity);

	        // xử lý ảnh nếu có file upload mới
	        if (productImages != null && !productImages.isEmpty()) {
	            UUID uuid = UUID.randomUUID();
	            String uuString = uuid.toString();
	            product.setImages(storageService.getSorageFilename(productImages, uuString));
	            storageService.store(productImages, product.getImages());
	        }

	        productService.save(product);
	        return new ResponseEntity<Response>(new Response(true, "Cập nhật thành công", product), HttpStatus.OK);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<Response>(new Response(false, "Cập nhật thất bại", null), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping(path = "/deleteProduct")
	public ResponseEntity<?> deleteProduct(@RequestParam("productId") Integer productId) {
	    Optional<ProductEntity> optProduct = productService.findById(productId);
	    if (!optProduct.isPresent()) {
	        return new ResponseEntity<Response>(new Response(false, "Sản phẩm không tồn tại", null), HttpStatus.NOT_FOUND);
	    }

	    try {
	        productService.deleteById(productId);
	        return new ResponseEntity<Response>(new Response(true, "Xóa sản phẩm thành công", null), HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<Response>(new Response(false, "Xóa sản phẩm thất bại", null), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



}
