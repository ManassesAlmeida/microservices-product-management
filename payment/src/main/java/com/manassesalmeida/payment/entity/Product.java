package com.manassesalmeida.payment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;

import com.manassesalmeida.payment.data.vo.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(nullable = false)
	private Integer stock;
	
	public static Product create(ProductVO productVO) {
		return new ModelMapper().map(productVO, Product.class);
	}
}
