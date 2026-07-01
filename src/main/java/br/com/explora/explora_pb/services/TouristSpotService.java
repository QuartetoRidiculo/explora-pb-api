package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.Favorite;
import br.com.explora.explora_pb.model.TouristSpot;
import br.com.explora.explora_pb.model.User;
import br.com.explora.explora_pb.repository.FavoriteRepository;
import br.com.explora.explora_pb.repository.TouristSpotRepository;
import br.com.explora.explora_pb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TouristSpotService {

    private final Logger logger = LoggerFactory.getLogger(TouristSpotService.class.getName());

    @Autowired
    private TouristSpotRepository repository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    public TouristSpot findById(Long id){
        logger.info("Finding tourist spot by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tourist spot not found!"));
    }

    public List<TouristSpot> findAll(){
        logger.info("Finding all tourist spots!");

        return repository.findAll();
    }

    public TouristSpot addTouristSpot(TouristSpot touristSpot){
        logger.info("Adding tourist spot!");

        return repository.save(touristSpot);
    }

    public TouristSpot updateTouristSpot(TouristSpot touristSpot){
        logger.info("Updating tourist spot!");

        TouristSpot touristSpotTest = repository.findById(touristSpot.getId())
                .orElseThrow(() -> new NotFoundException("Tourist spot not found!"));

        touristSpotTest.setCategory(touristSpot.getCategory());
        touristSpotTest.setCity(touristSpot.getCity());
        touristSpotTest.setAddress(touristSpot.getAddress());
        touristSpotTest.setName(touristSpot.getName());
        touristSpotTest.setDescription(touristSpot.getDescription());
        touristSpotTest.setOpeningHours(touristSpot.getOpeningHours());
        touristSpotTest.setAverageRating(touristSpot.getAverageRating());
        touristSpotTest.setImgUrl(touristSpot.getImgUrl());
        touristSpotTest.setLatitude(touristSpot.getLatitude());
        touristSpotTest.setLongitude(touristSpot.getLongitude());

        return repository.save(touristSpotTest);
    }

    public void deleteTouristSpot(Long id){
        logger.info("Deleting tourist spot!");

        TouristSpot touristSpotTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tourist spot not found!"));

        repository.delete(touristSpotTest);
    }

    public void addFavorite(Long touristSpotId, Long userId){

        TouristSpot touristSpot = repository.findById(touristSpotId).
                orElseThrow(() -> new NotFoundException("Tourist spot not found!"));

        User user = userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException("User not found!"));

        if(favoriteRepository.findByUser_IdAndTouristSpot_Id(userId, touristSpotId).isPresent()){
            throw new RuntimeException("This spot is already in favorites.");
        }

        Favorite favoriteTest = new Favorite();
        favoriteTest.setUser(user);
        favoriteTest.setTouristSpot(touristSpot);
        favoriteTest.setCreatedAt(LocalDateTime.now());

        favoriteRepository.save(favoriteTest);

    }

    public void deleteFavorite(Long touristSpotId, Long userId){

        Favorite favorite = favoriteRepository.findByUser_IdAndTouristSpot_Id(userId, touristSpotId).
                orElseThrow(() -> new NotFoundException("Favorite not found!"));

        favoriteRepository.delete(favorite);

    }

}
