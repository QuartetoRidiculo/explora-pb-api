package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
