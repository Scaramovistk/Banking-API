package com.capgemini.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	@GetMapping("/{id}")
	public Item getItemById(@PathVariable Long id) {
		return itemService.getItemById(id)
			.orElseThrow(() -> new RuntimeException("Item not found"));
	}

	@PostMapping
	public Item createItem(@RequestBody Item item) {
		return itemService.createItem(item);
	}

	@PutMapping("/{id}")
	public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
		return itemService.updateItem(id, updatedItem)
			.orElseThrow(() -> new RuntimeException("Item not found"));
	}

	@DeleteMapping("/{id}")
	public String deleteItem(@PathVariable Long id) {
		if (itemService.deleteItem(id)) {
			return "Item deleted successfully";
		} else {
			throw new RuntimeException("Item not found");
		}
	}
}

// Dependecy injection - Coponent
// Controle in a diferent path