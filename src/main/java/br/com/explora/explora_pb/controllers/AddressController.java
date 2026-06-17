package br.com.explora.explora_pb.controllers;

import br.com.explora.explora_pb.model.Address;
import br.com.explora.explora_pb.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Address> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Address findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Address createAddress(@RequestBody Address address){
        return services.addAddress(address);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Address updateAddress(@RequestBody Address address){
        return services.updateAddress(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id){

        services.deleteAddress(id);

        return ResponseEntity.noContent().build();
    }
}