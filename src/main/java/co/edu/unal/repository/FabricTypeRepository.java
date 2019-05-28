package co.edu.unal.repository;

import co.edu.unal.model.FabricType;
import co.edu.unal.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricTypeRepository extends JpaRepository<FabricType, Long> {

}
