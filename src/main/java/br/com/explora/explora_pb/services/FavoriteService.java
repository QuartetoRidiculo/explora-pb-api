package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.Favorite;
import br.com.explora.explora_pb.repository.FavoriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final Logger logger = LoggerFactory.getLogger(FavoriteService.class.getName());

    @Autowired
    private FavoriteRepository repository;

    public Favorite findById(Long id){
        logger.info("Finding favorite by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Favorite not found!"));
    }

    public List<Favorite> findAll(){
        logger.info("Finding all favorites!");

        return repository.findAll();
    }

    public Favorite addFavorite(Favorite favorite){
        logger.info("Adding favorite!");

        return repository.save(favorite);
    }

    public Favorite updateFavorite(Favorite favorite){
        logger.info("Updating favorite!");

        Favorite favoriteTest = repository.findById(favorite.getId())
                .orElseThrow(() -> new NotFoundException("Favorite not found!"));

        favoriteTest.setUser(favorite.getUser());
        favoriteTest.setTouristSpot(favorite.getTouristSpot());

        return repository.save(favoriteTest);
    }

    public void deleteFavorite(Long id){
        logger.info("Deleting favorite!");

        Favorite favoriteTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Favorite not found!"));

        repository.delete(favoriteTest);
    }
}
