package br.com.explora.explora_pb.controllers;

import br.com.explora.explora_pb.model.Favorite;
import br.com.explora.explora_pb.model.TouristSpot;
import br.com.explora.explora_pb.services.TouristSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/touristspot")
public class TouristSpotController {

    @Autowired
    TouristSpotService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TouristSpot> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TouristSpot findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TouristSpot createTouristSpot(@RequestBody TouristSpot touristSpot){
        return service.addTouristSpot(touristSpot);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TouristSpot updateTouristSpot(@RequestBody TouristSpot touristSpot){
        return service.updateTouristSpot(touristSpot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTouristSpot(@PathVariable("id") Long id){

        service.deleteTouristSpot(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<Void> addFavorite(
            @PathVariable Long id,
            @RequestParam Long userId) {

        service.addFavorite(id, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/favorite")
    public ResponseEntity<Void> removeFavorite(
            @PathVariable Long id,
            @RequestParam Long userId) {

        service.deleteFavorite(id, userId);
        return ResponseEntity.noContent().build();
    }

}