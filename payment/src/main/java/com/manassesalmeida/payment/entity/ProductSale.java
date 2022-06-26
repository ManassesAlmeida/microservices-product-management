package com.manassesalmeida.payment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;

import com.manassesalmeida.payment.data.vo.ProductSaleVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSale implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	private Integer amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Sale sale;
	
	public static ProductSale create(ProductSaleVO productSaleVO) {
		return new ModelMapper().map(productSaleVO, ProductSale.class);
	}
}
