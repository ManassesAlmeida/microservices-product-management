package com.manassesalmeida.payment.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.manassesalmeida.payment.entity.ProductSale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonPropertyOrder({"id", "productId", "amount"})
public class ProductSaleVO extends RepresentationModel<ProductSaleVO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long productId;
	private Integer amount;
	
	public static ProductSaleVO create(ProductSale productSale) {
		return new ModelMapper().map(productSale, ProductSaleVO.class);
	}
}
