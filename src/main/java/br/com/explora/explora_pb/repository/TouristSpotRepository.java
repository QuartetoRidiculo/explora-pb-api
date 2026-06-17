package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.TouristSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristSpotRepository extends JpaRepository<TouristSpot, Long> {
}
