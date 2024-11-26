package com.capgemini.app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
	private List<Item> items = new ArrayList<>();
	private Long nextId = 1L;

	public List<Item> getAllItems() {
		return items;
	}

	public Optional<Item> getItemById(Long id) {
		return items.stream().filter(item -> item.getId().equals(id)).findFirst();
	}

	public Item createItem(Item item) {
		item.setId(nextId++);
		items.add(item);
		return item;
	}

	public Optional<Item> updateItem(Long id, Item updatedItem) {
		return getItemById(id).map(item -> {
			item.setName(updatedItem.getName());
			item.setDescription(updatedItem.getDescription());
			return item;
		});
	}

	public boolean deleteItem(Long id) {
		return items.removeIf(item -> item.getId().equals(id));
	}
}
