package br.com.explora.explora_pb.controllers;

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
    TouristSpotService services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TouristSpot> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TouristSpot findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TouristSpot createTouristSpot(@RequestBody TouristSpot touristSpot){
        return services.addTouristSpot(touristSpot);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TouristSpot updateTouristSpot(@RequestBody TouristSpot touristSpot){
        return services.updateTouristSpot(touristSpot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTouristSpot(@PathVariable("id") Long id){

        services.deleteTouristSpot(id);

        return ResponseEntity.noContent().build();
    }
}