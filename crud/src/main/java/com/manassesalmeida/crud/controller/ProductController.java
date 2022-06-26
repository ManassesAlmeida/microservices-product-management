package com.manassesalmeida.crud.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manassesalmeida.crud.data.vo.ProductVO;
import com.manassesalmeida.crud.service.ProductService;

@RestController
@RequestMapping(value = "/products", produces = {"application/json"})
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private PagedResourcesAssembler<ProductVO> assembler;
	
	@GetMapping("/{id}")
	public ProductVO findById(@PathVariable("id") Long id) {		
		ProductVO productVO = productService.findById(id);
		productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getId())).withSelfRel());
		
		return productVO;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "asc".equalsIgnoreCase(direction) ? Direction.ASC : Direction.DESC;
		
		PageRequest pageRequest = PageRequest.of(page, limit, sortDirection, "name");
		Page<ProductVO> pageProducts = productService.findAll(pageRequest);
		pageProducts.stream().forEach(p -> p.add(linkTo(methodOn(ProductController.class).findById(p.getId())).withSelfRel()));
		PagedModel<EntityModel<ProductVO>> pagedModel = assembler.toModel(pageProducts);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping
	public ProductVO create(@RequestBody ProductVO productVO) {
		ProductVO productVONew = productService.create(productVO);
		productVONew.add(linkTo(methodOn(ProductController.class).findById(productVONew.getId())).withSelfRel());
		
		return productVONew;
	}
	
	@PutMapping
	public ProductVO update(@RequestBody ProductVO productVO) {
		ProductVO productVONew = productService.update(productVO);
		productVONew.add(linkTo(methodOn(ProductController.class).findById(productVONew.getId())).withSelfRel());
		
		return productVONew;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {		
		productService.delete(id);
		return ResponseEntity.ok().build();
	}
}
