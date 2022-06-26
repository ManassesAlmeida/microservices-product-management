package com.manassesalmeida.payment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manassesalmeida.payment.data.vo.SaleVO;
import com.manassesalmeida.payment.entity.ProductSale;
import com.manassesalmeida.payment.entity.Sale;
import com.manassesalmeida.payment.exception.ResourceNotFoundException;
import com.manassesalmeida.payment.repository.ProductSaleRepository;
import com.manassesalmeida.payment.repository.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private ProductSaleRepository productSaleRepository;
	
	public SaleVO create(SaleVO saleVO) {
		Sale sale = saleRepository.save(Sale.create(saleVO));
		
		List<ProductSale> productSales = new ArrayList<>();
		saleVO.getProducts().forEach(p -> {
			ProductSale ps = ProductSale.create(p);
			ps.setSale(sale);
			productSales.add(productSaleRepository.save(ps));
		});
		
		sale.setProducts(productSales);
		Sale saleNew = saleRepository.save(sale);
		
		return SaleVO.create(saleNew);
	}
	
	public Page<SaleVO> findAll(Pageable pageable){
		var page = saleRepository.findAll(pageable);
		return page.map(this::convertToSaleVO);
	}
	
	private SaleVO convertToSaleVO(Sale sale) {
		return SaleVO.create(sale);
	}
	
	public SaleVO findById(Long id) {
		var entity = saleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		return SaleVO.create(entity);
	}
	
	public void delete(Long id) {
		var entity = saleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		saleRepository.delete(entity);
	}
}
