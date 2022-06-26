package com.manassesalmeida.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manassesalmeida.crud.data.vo.ProductVO;
import com.manassesalmeida.crud.entity.Product;
import com.manassesalmeida.crud.exception.ResourceNotFoundException;
import com.manassesalmeida.crud.message.ProductSendMessage;
import com.manassesalmeida.crud.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductSendMessage productSendMessage;
	
	public ProductVO create(ProductVO productVO) {
		Product product = productRepository.save(Product.create(productVO));
		ProductVO productVONew = ProductVO.create(product);
		productSendMessage.sendMessage(productVONew);
		return productVONew;
	}
	
	public Page<ProductVO> findAll(Pageable pageable){
		var page = productRepository.findAll(pageable);
		return page.map(this::convertToProductVO);
	}
	
	private ProductVO convertToProductVO(Product product) {
		return ProductVO.create(product);
	}
	
	public ProductVO findById(Long id) {
		var entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		return ProductVO.create(entity);
	}
	
	public ProductVO update(ProductVO productVO) {
		final Optional<Product> optionalProduct = productRepository.findById(productVO.getId());
		if(optionalProduct.isEmpty()) {
			throw new ResourceNotFoundException("No records found for this id.");
		}
		
		Product product = productRepository.save(Product.create(productVO));
		return ProductVO.create(product);
	}
	
	public void delete(Long id) {
		var entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		productRepository.delete(entity);
	}
}
