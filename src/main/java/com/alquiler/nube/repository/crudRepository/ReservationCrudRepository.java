package com.alquiler.nube.repository.crudRepository;

import com.alquiler.nube.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
    public List<Reservation> findAllByStatus(String status);

    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    @Query("SELECT c.client, count (c.client) from Reservation AS c group by c.client order by count (c.client) desc ")
    public List<Object[]>countTotalReservationByClient();

}
