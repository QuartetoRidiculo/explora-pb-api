package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.Address;
import br.com.explora.explora_pb.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final Logger logger = LoggerFactory.getLogger(AddressService.class.getName());

    @Autowired
    private AddressRepository repository;

    public Address findById(Long id){
        logger.info("Finding address by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
    }

    public List<Address> findAll(){
        logger.info("Finding all addresses!");

        return repository.findAll();
    }

    public Address addAddress(Address address){
        logger.info("Adding address!");

        return repository.save(address);
    }

    public Address updateAddress(Address address){
        logger.info("Updating address!");

        Address addressTest = repository.findById(address.getId())
                .orElseThrow(() -> new NotFoundException("Address not found!"));

        addressTest.setStreet(address.getStreet());
        addressTest.setNumber(address.getNumber());
        addressTest.setDistrict(address.getDistrict());
        addressTest.setZipCode(address.getZipCode());
        addressTest.setState(address.getState());

        return repository.save(addressTest);
    }

    public void deleteAddress(Long id){
        logger.info("Deleting address!");

        Address addressTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));

        repository.delete(addressTest);
    }
}

