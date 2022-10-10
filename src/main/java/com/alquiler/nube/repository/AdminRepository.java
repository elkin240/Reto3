package com.alquiler.nube.repository;

import com.alquiler.nube.entities.Admin;
import com.alquiler.nube.repository.crudRepository.AdminCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {
    @Autowired
    private AdminCrudRepository adminCrudRepository;

    public List<Admin> getAll(){
        return (List<Admin>) adminCrudRepository.findAll();
    }
    public Optional<Admin> getAdmin(int id){
        return adminCrudRepository.findById(id);
    }
    public Admin save(Admin r){
        return adminCrudRepository.save(r);
    }
    public void delete(Admin r){
        adminCrudRepository.delete(r);
    }
}
