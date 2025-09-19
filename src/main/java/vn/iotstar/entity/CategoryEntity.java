package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class CategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
	private int categoryId;
	
	@Column(name = "CategoryName", columnDefinition = "NVARCHAR(MAX)")
	private String categoryName;
	
	@Column(name = "Images", columnDefinition = "NVARCHAR(MAX)")
	private String images;
	
	@Column(name = "Status", columnDefinition = "INT")
	private int status;
	

}
