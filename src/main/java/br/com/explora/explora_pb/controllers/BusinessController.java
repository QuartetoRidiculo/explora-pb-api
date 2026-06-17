package br.com.explora.explora_pb.controllers;

import br.com.explora.explora_pb.model.Business;
import br.com.explora.explora_pb.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessService services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Business> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Business findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Business createBusiness(@RequestBody Business business){
        return services.addBusiness(business);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Business updateBusiness(@RequestBody Business business){
        return services.updateBusiness(business);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBusiness(@PathVariable("id") Long id){

        services.deleteBusiness(id);

        return ResponseEntity.noContent().build();
    }
}