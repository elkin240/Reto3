package com.alquiler.nube.controller;


import com.alquiler.nube.entities.Reservation;
import com.alquiler.nube.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService ReservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return ReservationService.getAll();
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody  Reservation p){
        return ReservationService.save(p);
    }
}
