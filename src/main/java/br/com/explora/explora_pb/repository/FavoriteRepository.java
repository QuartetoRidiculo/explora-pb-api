package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUser_IdAndTouristSpot_Id(Long userId, Long touristSpotId);

}
