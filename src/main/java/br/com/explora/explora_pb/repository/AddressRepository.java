package br.com.explora.explora_pb.repository;

import br.com.explora.explora_pb.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
