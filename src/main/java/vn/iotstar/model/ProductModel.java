package vn.iotstar.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iotstar.entity.CategoryEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer productId;
	private String productName;
	private int quantity;
	
	private double unitPrice;
	
	private String images;
	
	private MultipartFile imageFile;
	
	private String description;
	
	private double discount;
	private Date createDate;
	
	private short status;
	
	private CategoryEntity category;
}
