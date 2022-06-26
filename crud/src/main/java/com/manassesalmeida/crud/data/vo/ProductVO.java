package com.manassesalmeida.crud.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.manassesalmeida.crud.entity.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonPropertyOrder({"id", "name", "stock", "price"})
public class ProductVO extends RepresentationModel<ProductVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer stock;
	private Double price;
	
	public static ProductVO create(Product product) {
		return new ModelMapper().map(product, ProductVO.class);
	}
}
