package com.product;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;

@RestController
public class CtrlProduct {

	
	@GetMapping("/category")
	public ArrayList<Category> registroCategorias() {
		
		// Lista de categorias
		ArrayList<Category> listaCategorias = new ArrayList<>();
		
		// Agregando objetos categorias
		listaCategorias.add(new Category(1, "Linea Blanca", "LB"));
		listaCategorias.add(new Category(2, "Electronica", "Electr"));
		listaCategorias.add(new Category(3, "Cocina", "Coc"));
		listaCategorias.add(new Category(4, "Comida", "Com"));
		listaCategorias.add(new Category(5, "Herreria", "Herr"));
		
		return listaCategorias;
		
	}
}
