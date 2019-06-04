package co.edu.unal.controller;

import co.edu.unal.exception.ResourceNotFoundException;
import co.edu.unal.model.ClothingItem;
import co.edu.unal.repository.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributeValueException;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/logistics")
public class ClothingItemController {

	@Autowired
	ClothingItemRepository itemRepository;

	// Get All Items
	@GetMapping("/items")
	public List<ClothingItem> getAllItems() {
	    return itemRepository.findAll();
	}
	
	// Get a Single Item
	@GetMapping("/items/{id}")
	public ClothingItem getItemById(@PathVariable(value = "id") Long itemId) {
	    return itemRepository.findById(itemId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", itemId));
	}
	
	// Get all items for room
	@GetMapping("/items/room/{id}")
	public List<ClothingItem> getAllItemsOfRoom(@PathVariable(value = "id") Integer roomId) {
		List<ClothingItem> allRooms = itemRepository.findAll();
		List<ClothingItem> thisRoom = new ArrayList<ClothingItem>();
		for(ClothingItem i : allRooms)
			if(i.getRoomId() == roomId)
				thisRoom.add(i);
	    return thisRoom;
	}	
	
	// Create a new Item
	@PostMapping("/items")
	public ClothingItem createItem(@Valid @RequestBody ClothingItem item) throws InvalidAttributeValueException {
		if(item.getRequires_ironing() || item.getRequires_washing())
			return itemRepository.save(item);
		else
			//Can't accept an item that requires neither ironing nor washing
			throw new InvalidAttributeValueException("Can't receive an item that doesn't require either washing or ironing!");
	}

	// Update a Item
	@PutMapping("/items/{id}")
	public ClothingItem updateItem(@PathVariable(value = "id") Long itemId,
	                                        @Valid @RequestBody ClothingItem itemDetails) throws InvalidAttributeValueException {

	    ClothingItem item = itemRepository.findById(itemId)
	            .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

	    item.setRoomId(itemDetails.getRoomId());
	    item.setRequires_washing(itemDetails.getRequires_washing());
	    item.setRequires_ironing(itemDetails.getRequires_ironing());
	    item.setReceivedAt(itemDetails.getReceivedAt());
	    //Can't deliver an item that still requires ironing or washing
	    if(itemDetails.hasBeenDelivered())
	    	if(itemDetails.getRequires_ironing() || itemDetails.getRequires_washing())
	    		throw new InvalidAttributeValueException("Can't deliver a clothing item that isn't ready!");
	    	else
	    		item.setDeliveredAt(itemDetails.getDeliveredAt());

	    ClothingItem updatedItem = itemRepository.save(item);
	    return updatedItem;
	}

	// Delete a Item
	@DeleteMapping("/items/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
	    ClothingItem item = itemRepository.findById(itemId)
	            .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

	    itemRepository.delete(item);

	    return ResponseEntity.ok().build();
	}


	
}
