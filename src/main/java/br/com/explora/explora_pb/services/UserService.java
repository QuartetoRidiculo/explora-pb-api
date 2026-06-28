package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.User;
import br.com.explora.explora_pb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;


    //Buscar por id
    public User findById(Long id){
        logger.info("Finding user by id!");

        return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    //Função para pegar todos
    public List<User> findAll(){
        logger.info("Finding all users!");

        return repository.findAll();
    }

    //Cadastrar usuario
    public User addUser(User user){
        logger.info("Adding user!");

        //Salvando a senha criptografada
        user.setPassword(encoder.encode(user.getPassword()));

        return repository.save(user);
    }

    //Atualizar usuário
    public User updateUser(User user){
        logger.info("Updating user!");

        User userTest = repository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found!"));

        userTest.setFirstName(user.getFirstName());
        userTest.setLastName(user.getLastName());
        userTest.setEmail(user.getEmail());
        userTest.setPassword(user.getPassword());

        return repository.save(userTest);
    }

    public void deleteUser(Long id){

        logger.info("Deleting user!");

        User userTest = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));

        repository.delete(userTest);
    }

}
