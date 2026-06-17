package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
