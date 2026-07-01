package br.com.explora.explora_pb.data.dto;

import br.com.explora.explora_pb.enums.Role;
import br.com.explora.explora_pb.model.User;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role
) {
    public static UserDTO from(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole());
    }
}