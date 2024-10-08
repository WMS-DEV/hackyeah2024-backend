package pl.wmsdev.sportly.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wmsdev.sportly.dto.CategoryDTO;
import pl.wmsdev.sportly.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	@Operation(summary = "Get all categories")
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		return ResponseEntity.ok(categoryService.getCategories());
	}
}
