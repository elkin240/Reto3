package com.alquiler.nube.controller;

import com.alquiler.nube.entities.DTOs.CompletedAndCanceled;
import com.alquiler.nube.entities.DTOs.TotalAndClient;
import com.alquiler.nube.entities.Reservation;
import com.alquiler.nube.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation p) {
        return reservationService.save(p);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation p) {
        return reservationService.update(p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return reservationService.delete(id);
    }

    @GetMapping("/report-dates/{fecha1}/{fecha2}")
    public List<Reservation> getReservationStatusReport(@PathVariable("fecha1") String fecha1, @PathVariable("fecha2") String fecha2) {
        return reservationService.getReservationsBetweenDatesReport(fecha1, fecha2);
    }

    @GetMapping("/report-status")
    public CompletedAndCanceled getReservationStatusReport() {
        return reservationService.getReservationStatusReport();
    }

    @GetMapping ("/report-clients")
    public List<TotalAndClient> getTopClientesReport() {
        return reservationService.getTopClientesReport();

    }

}
