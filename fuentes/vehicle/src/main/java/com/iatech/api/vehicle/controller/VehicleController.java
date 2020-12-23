package com.iatech.api.vehicle.controller;

import java.util.List;
import javax.validation.Valid;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.iatech.api.vehicle.dao.VehicleDao;
import com.iatech.api.vehicle.exception.VehicleNotFoundException;
import com.iatech.api.vehicle.model.Vehicle;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	private final Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@GetMapping
	public List<Vehicle> getAll() {
		logger.debug("Inicio del metodo getAll");
		logger.error("asd");
		return vehicleDao.findAll();
	}
	
	@GetMapping(path="/{id}")
	public Vehicle getById(@PathVariable Long id) {
		logger.debug("Inicio del metodo getById");
		Optional<Vehicle> vehicle = vehicleDao.findById(id);
		if(!vehicle.isPresent()) {
			logger.warn("Vehicle not found");
			throw new VehicleNotFoundException("ID - " + id);
		}
		return vehicle.get();
	}
	
	@GetMapping(path="/plaque/{plaque}")
	public Vehicle getByPlaque(@PathVariable String plaque) {
		logger.debug("Inicio del metodo getByPlaque");
		Optional<Vehicle> vehicle = vehicleDao.getByPlaque(plaque);
		if(!vehicle.isPresent()) {
			logger.warn("Vehicle not found");
			throw new VehicleNotFoundException("Plaque - " + plaque);
		}
		return vehicle.get();
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Vehicle vehicle) {
		logger.debug("Inicio del metodo create");
		Vehicle vehicleCreated = vehicleDao.save(vehicle);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehicleCreated.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Object> update(@RequestBody Vehicle vehicle, @PathVariable Long id) {
		logger.debug("Inicio del metodo update");
		Optional<Vehicle> vehicleDB = vehicleDao.findById(id);
		if(!vehicleDB.isPresent()) {
			logger.warn("Vehicle not found");
			throw new VehicleNotFoundException("ID - " + id);
		}
		
		vehicle.setId(id);
		vehicleDao.save(vehicle);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		logger.debug("Inicio del metodo remove");
		Optional<Vehicle> vehicle = vehicleDao.findById(id);
		
		if(!vehicle.isPresent()) {
			logger.warn("Vehicle not found");
			throw new VehicleNotFoundException("ID - " + id);
		}
		vehicleDao.delete(vehicle.get());
		return ResponseEntity.ok().build();
	}

}
