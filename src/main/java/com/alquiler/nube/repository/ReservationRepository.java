package com.alquiler.nube.repository;

import com.alquiler.nube.entities.Client;
import com.alquiler.nube.entities.DTOs.TotalAndClient;
import com.alquiler.nube.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.alquiler.nube.repository.crudRepository.ReservationCrudRepository;
import java.util.ArrayList;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }
    public Reservation save(Reservation r){
        return reservationCrudRepository.save(r);
    }
    public void delete(Reservation r){reservationCrudRepository.delete(r);}
    
    public List<Reservation> getReservationsBetweenDates (Date fechaA, Date fechaB ){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(fechaA, fechaB);
    }
    
    public List<Reservation> getReservationByStstus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<TotalAndClient> getTopClients(){
        List<TotalAndClient> respuesta = new ArrayList<>();
        List<Object[]> reporte = reservationCrudRepository.getTotalReservationByClient();
        for(int i=0; i<reporte.size(); i++ ){
            respuesta.add(new TotalAndClient((long)reporte.get(i)[1],(Client) reporte.get(i)[0] ));
        } 
        return respuesta;
    }
}
