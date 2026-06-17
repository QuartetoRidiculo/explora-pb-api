package br.com.explora.explora_pb.controllers;

import br.com.explora.explora_pb.model.City;
import br.com.explora.explora_pb.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public City findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public City createCity(@RequestBody City city){
        return services.addCity(city);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public City updateCity(@RequestBody City city){
        return services.updateCity(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") Long id){

        services.deleteCity(id);

        return ResponseEntity.noContent().build();
    }
}