package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.Log;
import br.com.explora.explora_pb.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final Logger logger = LoggerFactory.getLogger(LogService.class.getName());

    @Autowired
    private LogRepository repository;

    public Log findById(Long id){
        logger.info("Finding log by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Log not found!"));
    }

    public List<Log> findAll(){
        logger.info("Finding all logs!");

        return repository.findAll();
    }

    public Log addLog(Log log){
        logger.info("Adding log!");

        return repository.save(log);
    }

    public Log updateLog(Log log){
        logger.info("Updating log!");

        Log logTest = repository.findById(log.getId())
                .orElseThrow(() -> new NotFoundException("Log not found!"));

        logTest.setActionDescription(log.getActionDescription());

        return repository.save(logTest);
    }

    public void deleteLog(Long id){
        logger.info("Deleting log!");

        Log logTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Log not found!"));

        repository.delete(logTest);
    }
}
