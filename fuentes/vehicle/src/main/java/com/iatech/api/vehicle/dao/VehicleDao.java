package com.iatech.api.vehicle.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iatech.api.vehicle.model.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Long> {
	Optional<Vehicle> getByPlaque(String plaque);
}
