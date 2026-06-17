package br.com.explora.explora_pb.controllers;

import br.com.explora.explora_pb.model.Log;
import br.com.explora.explora_pb.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Log> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Log findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Log createLog(@RequestBody Log log){
        return services.addLog(log);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Log updateLog(@RequestBody Log log){
        return services.updateLog(log);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable("id") Long id){

        services.deleteLog(id);

        return ResponseEntity.noContent().build();
    }
}