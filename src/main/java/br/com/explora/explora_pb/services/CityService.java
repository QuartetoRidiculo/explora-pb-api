package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.City;
import br.com.explora.explora_pb.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final Logger logger = LoggerFactory.getLogger(CityService.class.getName());

    @Autowired
    private CityRepository repository;

    public City findById(Long id){
        logger.info("Finding city by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("City not found!"));
    }

    public List<City> findAll(){
        logger.info("Finding all cities!");

        return repository.findAll();
    }

    public City addCity(City city){
        logger.info("Adding city!");

        return repository.save(city);
    }

    public City updateCity(City city){
        logger.info("Updating city!");

        City cityTest = repository.findById(city.getId())
                .orElseThrow(() -> new NotFoundException("City not found!"));

        cityTest.setName(city.getName());
        cityTest.setState(city.getState());
        cityTest.setDescription(city.getDescription());
        cityTest.setAddress(city.getAddress());

        return repository.save(cityTest);
    }

    public void deleteCity(Long id){
        logger.info("Deleting city!");

        City cityTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("City not found!"));

        repository.delete(cityTest);
    }
}
