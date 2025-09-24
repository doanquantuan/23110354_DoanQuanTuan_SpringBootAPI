package vn.iotstar.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int categoryId;
	private String categoryName;
	private String images;
	
	private MultipartFile icon;
	
	private int status;
	private int userId;
	
	private Boolean isEdit=false;
}
