package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    private final String secret = "alksjfdoejfjasnndskajxc";

    public String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create().withSubject(user.getEmail()).withExpiresAt(Instant.now().plus(Duration.ofDays(7)))
                .sign(algorithm);

    }

    public String validateToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.require(algorithm).build().verify(token).getSubject();

    }

}
