package com.alquiler.nube.controller;

import com.alquiler.nube.entities.Admin;
import com.alquiler.nube.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Admin")
public class AdminController {

    @Autowired
    private com.alquiler.nube.service.AdminService AdminService;

    @GetMapping("/all")
    public List<Admin> getAll(){
        return AdminService.getAll();
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin save(@RequestBody  Admin p){
        return AdminService.save(p);
    }
}
