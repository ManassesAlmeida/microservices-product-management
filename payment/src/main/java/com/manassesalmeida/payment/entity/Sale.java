package com.manassesalmeida.payment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.manassesalmeida.payment.data.vo.SaleVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date timestamp;
	
	@Column(nullable = false)
	private Double totalAmount;
	
	@OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<ProductSale> products;
	
	public static Sale create(SaleVO saleVO) {
		return new ModelMapper().map(saleVO, Sale.class);
	}
}
