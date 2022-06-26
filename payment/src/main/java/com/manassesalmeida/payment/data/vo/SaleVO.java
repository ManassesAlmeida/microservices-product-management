package com.manassesalmeida.payment.data.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.manassesalmeida.payment.entity.Sale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonPropertyOrder({"id", "timestamp", "totalAmount", "products"})
public class SaleVO extends RepresentationModel<SaleVO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date timestamp;
	private Double totalAmount;
	private List<ProductSaleVO> products;
	
	public static SaleVO create(Sale sale) {
		return new ModelMapper().map(sale, SaleVO.class);
	}
}
