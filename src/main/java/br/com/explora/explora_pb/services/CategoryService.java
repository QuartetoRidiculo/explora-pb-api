package br.com.explora.explora_pb.services;

import br.com.explora.explora_pb.exception.NotFoundException;
import br.com.explora.explora_pb.model.Category;
import br.com.explora.explora_pb.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class.getName());

    @Autowired
    private CategoryRepository repository;

    public Category findById(Long id){
        logger.info("Finding category by id!");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    public List<Category> findAll(){
        logger.info("Finding all categories!");

        return repository.findAll();
    }

    public Category addCategory(Category category){
        logger.info("Adding category!");

        return repository.save(category);
    }

    public Category updateCategory(Category category){
        logger.info("Updating category!");

        Category categoryTest = repository.findById(category.getId())
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        categoryTest.setName(category.getName());

        return repository.save(categoryTest);
    }

    public void deleteCategory(Long id){
        logger.info("Deleting category!");

        Category categoryTest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        repository.delete(categoryTest);
    }
}