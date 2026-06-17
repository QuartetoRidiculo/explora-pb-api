package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
