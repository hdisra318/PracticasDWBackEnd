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

	/* Lista de Categorias */
	List<Category> categories = new ArrayList<>();
	
	@GetMapping
	public ResponseEntity<List<Category>> getCategories(){
		
		/* Categorias */
		Category category1 = new Category(1, "Linea Blanca", "LB", 1);
		Category category2 = new Category(2, "Electronica", "Electr", 1);
		
		categories.add(category1);
		categories.add(category2);
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/{category_id}")
	public ResponseEntity<Category> getCategory(@PathVariable int category_id){
		
		
		boolean existeCategoria = false;
		Category cat = null;
		
		for(int i = 0; i<categories.size(); ++i) {
			
			if(categories.get(i).getCategory_id().intValue() == category_id){
				existeCategoria = true;
				cat = categories.get(i);
				break;
			}
		}
		
		
		if(existeCategoria) {
			
			return new ResponseEntity<>(cat, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping
	public ResponseEntity<String> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult){
		
		String message = "";
		
		// Si se encontraron errores en el objeto category
		if(bindingResult.hasErrors()) {
			
			//Obteniendo el mensaje del primer error
			message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		// Para verificar si es la misma categoria
		boolean igCat = false;

		for(int i = 0; i<categories.size(); ++i) {
			if(category.getCategory().equals(categories.get(i).getCategory()) &&
					category.getAcronym().equals(categories.get(i).getAcronym())){
				
				igCat = true;
				break;
			}
		}
				
		if(igCat) {
					
			message = "category already exists";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		message = "category created";
		
		return new ResponseEntity<>(message, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/{category_id}")
	public ResponseEntity<String> updateCategory(@PathVariable int category_id, @Valid @RequestBody Category category, BindingResult bindingResult){
		
		String message = "";
		
		if(bindingResult.hasErrors()) {
			
			message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		
		// Para verificar si el category_id es distinto que los de la lista
		boolean difId = true;

		for(int i = 0; i<categories.size(); ++i) {
			if(category_id == categories.get(i).getCategory_id().intValue())
				difId = false;
		}
		
		if(difId) {
			
			message = "category does not exist";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		
		// Verificar si la categoria pasada ya estaba en la lista
		for(int i = 0; i<categories.size(); i++) {
			if(category.getCategory().equals(categories.get(i).getCategory()) && 
					category.getAcronym().equals(categories.get(i).getAcronym())) {
				
				message = "category alredy exists";
				return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			}
				
		}
		
		message = "category updated";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int category_id){
		
		String message = "";
		
		// Para verificar si el category_id existe en la lista
		boolean dontExistId = true;

		for(int i = 0; i<categories.size(); ++i) {
			if(category_id == categories.get(i).getCategory_id().intValue())
				dontExistId = false;
		}
				
		if(dontExistId) {
					
			message = "category does not exist";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		message = "category removed";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
