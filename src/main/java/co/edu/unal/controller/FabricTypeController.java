package co.edu.unal.controller;

import co.edu.unal.exception.ResourceNotFoundException;
import co.edu.unal.model.ClothingItem;
import co.edu.unal.model.FabricType;
import co.edu.unal.model.Room;
import co.edu.unal.repository.ClothingItemRepository;
import co.edu.unal.repository.FabricTypeRepository;
import co.edu.unal.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FabricTypeController {

	@Autowired
	FabricTypeRepository fabricTypeRepository;

	// Get All FabricTypes
	@GetMapping("/fabricTypes")
	public List<FabricType> getAllFabricTypes() {
	    return fabricTypeRepository.findAll();
	}
	
	// Get a Single FabricType
	@GetMapping("/fabricTypes/{id}")
	public FabricType getFabricTypeById(@PathVariable(value = "id") Long fabricTypeId) {
	    return fabricTypeRepository.findById(fabricTypeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", fabricTypeId));
	}
	
	// Create a new FabricType
	@PostMapping("/fabricTypes")
	public FabricType createFabricType(@Valid @RequestBody FabricType fabricType) {
	    return fabricTypeRepository.save(fabricType);
	}

	// Update a FabricType
	@PutMapping("/fabricTypes/{id}")
	public FabricType updateFabricType(@PathVariable(value = "id") Long fabricTypeId,
	                                        @Valid @RequestBody FabricType fabricTypeDetails) {

	    FabricType fabricType = fabricTypeRepository.findById(fabricTypeId)
	            .orElseThrow(() -> new ResourceNotFoundException("FabricType", "id", fabricTypeId));

	    FabricType updatedFabricType = fabricTypeRepository.save(fabricType);
	    return updatedFabricType;
	}

	// Delete a FabricType
	@DeleteMapping("/fabricTypes/{id}")
	public ResponseEntity<?> deleteFabricType(@PathVariable(value = "id") Long fabricTypeId) {
	    FabricType fabricType = fabricTypeRepository.findById(fabricTypeId)
	            .orElseThrow(() -> new ResourceNotFoundException("FabricType", "id", fabricTypeId));

	    fabricTypeRepository.delete(fabricType);

	    return ResponseEntity.ok().build();
	}


	
}
