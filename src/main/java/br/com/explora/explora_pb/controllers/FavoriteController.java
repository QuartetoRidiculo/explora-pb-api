package br.com.explora.explora_pb.controllers;

import br.com.explora.explora_pb.model.Favorite;
import br.com.explora.explora_pb.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Favorite> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Favorite findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Favorite createFavorite(@RequestBody Favorite favorite){
        return services.addFavorite(favorite);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Favorite updateFavorite(@RequestBody Favorite favorite){
        return services.updateFavorite(favorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable("id") Long id){

        services.deleteFavorite(id);

        return ResponseEntity.noContent().build();
    }
}