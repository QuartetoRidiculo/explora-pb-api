package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.Review;
import br.com.explora.explora_pb.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final Logger logger = LoggerFactory.getLogger(ReviewService.class.getName());

    @Autowired
    private ReviewRepository repository;

    public Review findById(Long id){
        logger.info("Finding review by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found!"));
    }

    public List<Review> findAll(){
        logger.info("Finding all reviews!");

        return repository.findAll();
    }

    public Review addReview(Review review){
        logger.info("Adding review!");

        return repository.save(review);
    }

    public Review updateReview(Review review){
        logger.info("Updating review!");

        Review reviewTest = repository.findById(review.getId())
                .orElseThrow(() -> new NotFoundException("Review not found!"));

        reviewTest.setUser(review.getUser());
        reviewTest.setTouristSpot(review.getTouristSpot());
        reviewTest.setGrade(review.getGrade());
        reviewTest.setContent(review.getContent());

        return repository.save(reviewTest);
    }

    public void deleteReview(Long id){
        logger.info("Deleting review!");

        Review reviewTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found!"));

        repository.delete(reviewTest);
    }
}
