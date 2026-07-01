package br.com.explora.explora_pb.controllers;


import br.com.explora.explora_pb.data.dto.LoginRequest;
import br.com.explora.explora_pb.data.dto.UserDTO;
import br.com.explora.explora_pb.model.User;
import br.com.explora.explora_pb.services.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();

        String token = tokenService.generateToken(user);

        ResponseCookie cookie = ResponseCookie.from("jwt", token).httpOnly(true).secure(false)
                .path("/").maxAge(Duration.ofDays(7)).sameSite("Lax").build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("Login successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false) // true em produção (HTTPS)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole()));
    }
}
