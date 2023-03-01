package com.product.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;

@RestController
@RequestMapping("/category")
public class CtrlCategory {

	
	/* Categorias */
	Category category1 = new Category(1, "Linea Blanca", "LB", 1);
	Category category2 = new Category(2, "Electronica", "Electr", 1);
	
	@GetMapping
	public ResponseEntity<List<Category>> getCategories(){
		
		List<Category> categories = new ArrayList<>();
		categories.add(category1);
		categories.add(category2);
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/{category_id}")
	public ResponseEntity<Category> getCategory(@PathVariable int category_id){
		
		return new ResponseEntity<>(category1, HttpStatus.OK);
		
	}
	
	
	@PostMapping
	public ResponseEntity<String> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult){
		
		String message = "";
		
		System.out.println(category);
		category.setCategory_id(3);
		System.out.println(category);
		
		
		// Si se encontraron errores en el objeto category
		if(bindingResult.hasErrors()) {
			
			//Obteniendo el mensaje del primer error
			message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		if(category.getCategory_id().intValue() == category1.getCategory_id().intValue() ||
				category.getCategory_id().intValue() == category2.getCategory_id().intValue()) {
			
			message = "category already exist";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		message = "category created";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{category_id}")
	public ResponseEntity<String> updateCategory(@PathVariable int category_id, @Valid @RequestBody Category category, BindingResult bindingResult){
		
		String message = "";
		
		if(bindingResult.hasErrors()) {
			
			message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		if(category_id != category1.getCategory_id().intValue() && category_id != category2.getCategory_id().intValue()) {
			
			message = "category does not exist";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		message = "category updated";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int category_id){
		
		String message = "";
		
		if(category_id != category1.getCategory_id().intValue() && category_id != category2.getCategory_id().intValue()) {
			
			message = "category does not exist";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		message = "category removed";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
