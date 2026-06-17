package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.Business;
import br.com.explora.explora_pb.repository.BusinessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    private final Logger logger = LoggerFactory.getLogger(BusinessService.class.getName());

    @Autowired
    private BusinessRepository repository;

    public Business findById(Long id){
        logger.info("Finding business by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Business not found!"));
    }

    public List<Business> findAll(){
        logger.info("Finding all businesses!");

        return repository.findAll();
    }

    public Business addBusiness(Business business){
        logger.info("Adding business!");

        return repository.save(business);
    }

    public Business updateBusiness(Business business){
        logger.info("Updating business!");

        Business businessTest = repository.findById(business.getId())
                .orElseThrow(() -> new NotFoundException("Business not found!"));

        businessTest.setCity(business.getCity());
        businessTest.setAddress(business.getAddress());
        businessTest.setName(business.getName());
        businessTest.setOpeningHours(business.getOpeningHours());
        businessTest.setImgUrl(business.getImgUrl());

        return repository.save(businessTest);
    }

    public void deleteBusiness(Long id){
        logger.info("Deleting business!");

        Business businessTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Business not found!"));

        repository.delete(businessTest);
    }
}
