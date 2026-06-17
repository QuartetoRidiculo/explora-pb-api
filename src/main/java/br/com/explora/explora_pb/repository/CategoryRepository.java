package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
