package com.alquiler.nube.service;

import com.alquiler.nube.entities.Category;
import com.alquiler.nube.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getProduct(int id) {
        return categoryRepository.getCategory(id);
    }

    public Category save(Category p) {
        if (p.getId() == null) {
            return categoryRepository.save(p);
        } else {
            Optional<Category> e = categoryRepository.getCategory(p.getId());
            if (e.isPresent()) {
                return p;
            } else {
                return categoryRepository.save(p);
            }
        }
    }

//    public Category update(Category category) {
//        if (category.getId() != null) {
//            Optional<Category> q = categoryRepository.getCategory(category.getId());
//            if (!q.isEmpty()) {
//              if(category.getDescription()!=null){
//                    q.get().setDescription(category.getDescription());
//                }  
//              
//              if(category.getName()!=null){
//                    q.get().setName(category.getName());
//                }
//              return categoryRepository.save(q.get());
//            }
//        }
//        return category;
//    }
//        

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category> q = categoryRepository.getCategory(category.getId());
            if(q.isPresent()){
                if(category.getName()!=null){
                    q.get().setName(category.getName());
                }
                if(category.getDescription()!=null){
                    q.get().setDescription(category.getDescription());
                }
                categoryRepository.save(q.get());
                return q.get();
            }else{
                return category;
            }
        }else{
            return category;
        }
    }
    public boolean delete(int id) {
        boolean flag = false;
        Optional<Category> p = categoryRepository.getCategory(id);
        if (p.isPresent()) {
            categoryRepository.delete(p.get());
            flag = true;
        }
        return flag;

    }

}
