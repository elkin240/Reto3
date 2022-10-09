package com.alquiler.nube.repository.crudRepository;

import com.alquiler.nube.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
}
