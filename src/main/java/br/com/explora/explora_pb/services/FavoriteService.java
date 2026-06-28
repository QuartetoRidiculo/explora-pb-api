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

    public Favorite findById(Long id) {
        logger.info("Finding favorite by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Favorite not found!"));
    }

    public List<Favorite> findAll() {
        logger.info("Finding all favorites!");

        return repository.findAll();
    }

}
