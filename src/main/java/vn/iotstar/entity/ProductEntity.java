package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(length = 500, columnDefinition = "nvarchar(500) not null")
	private String productName;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private double unitPrice;
	
	@Column(length = 200)
	private String images;
	
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String description;
	
	@Column(nullable = false)
	private double discount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "YYYY-MM-DD hh:mi:ss")
	private Date createDate;
	
	@Column(nullable = false)
	private short status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryEntity category;

}
