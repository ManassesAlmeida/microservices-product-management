package com.manassesalmeida.payment.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manassesalmeida.payment.data.vo.SaleVO;
import com.manassesalmeida.payment.service.SaleService;

@RestController
@RequestMapping(value = "/sales", produces = "application/json")
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	@Autowired
	private PagedResourcesAssembler<SaleVO> assembler;
	
	@GetMapping("/{id}")
	public SaleVO findById(@PathVariable("id") Long id) {		
		SaleVO saleVO = saleService.findById(id);
		saleVO.add(linkTo(methodOn(SaleController.class).findById(saleVO.getId())).withSelfRel());
		
		return saleVO;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "asc".equalsIgnoreCase(direction) ? Direction.ASC : Direction.DESC;
		
		PageRequest pageRequest = PageRequest.of(page, limit, sortDirection, "timestamp");
		Page<SaleVO> pageSales = saleService.findAll(pageRequest);
		pageSales.stream().forEach(p -> p.add(linkTo(methodOn(SaleController.class).findById(p.getId())).withSelfRel()));
		PagedModel<EntityModel<SaleVO>> pagedModel = assembler.toModel(pageSales);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping
	public SaleVO create(@RequestBody SaleVO saleVO) {
		SaleVO saleVONew = saleService.create(saleVO);
		saleVONew.add(linkTo(methodOn(SaleController.class).findById(saleVONew.getId())).withSelfRel());
		
		return saleVONew;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {		
		saleService.delete(id);
		return ResponseEntity.ok().build();
	}
}
