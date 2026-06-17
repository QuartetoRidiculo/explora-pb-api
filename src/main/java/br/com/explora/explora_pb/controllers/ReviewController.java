package br.com.explora.explora_pb.controllers;

import br.com.explora.explora_pb.model.Review;
import br.com.explora.explora_pb.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Review> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Review findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Review createReview(@RequestBody Review review){
        return services.addReview(review);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Review updateReview(@RequestBody Review review){
        return services.updateReview(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable("id") Long id){

        services.deleteReview(id);

        return ResponseEntity.noContent().build();
    }
}