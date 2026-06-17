package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
