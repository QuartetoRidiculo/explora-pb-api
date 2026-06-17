package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
