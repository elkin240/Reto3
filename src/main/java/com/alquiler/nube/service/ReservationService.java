package com.alquiler.nube.service;

import com.alquiler.nube.entities.DTOs.CompletedAndCanceled;
import com.alquiler.nube.entities.DTOs.TotalAndClient;
import com.alquiler.nube.entities.Reservation;
import com.alquiler.nube.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation p) {
        if (p.getIdReservation() == null) {
            if (p.getStatus() != null) {

            } else {
                p.setStatus("created");

            }
            ;
            return reservationRepository.save(p);
        } else {
            Optional<Reservation> e = reservationRepository.getReservation(p.getIdReservation());
            if (e.isPresent()) {
                return p;
            } else {
                return reservationRepository.save(p);
            }
        }
    }

    public Reservation update(Reservation p) {
        if (p.getIdReservation() != null) {
            Optional<Reservation> q = reservationRepository.getReservation(p.getIdReservation());
            if (q.isPresent()) {

                if (p.getDevolutionDate() != null) {
                    q.get().setDevolutionDate(p.getDevolutionDate());
                }
                if (p.getStartDate() != null) {
                    q.get().setStartDate(p.getStartDate());
                }

                if (p.getScore() != null) {
                    q.get().setScore(p.getScore());
                }

                if (p.getStatus() != null) {
                    q.get().setStatus(p.getStatus());
                }

                reservationRepository.save(q.get());
                return q.get();
            } else {
                return p;
            }
        } else {
            return p;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Reservation> p = reservationRepository.getReservation(id);
        if (p.isPresent()) {
            reservationRepository.delete(p.get());
            flag = true;
        }
        return flag;
    }

    public List<Reservation> getReservationsBetweenDatesReport(String fechaA, String fechaB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date a = new Date();
        Date b = new Date();
        try {
            a = parser.parse(fechaA);
            b = parser.parse(fechaB);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        if (a.before(b)) {
            return reservationRepository.getReservationsBetweenDates(a, b);
        } else {
            return new ArrayList<>();
        }
    }

    public CompletedAndCanceled getReservationStatusReport() {
        List<Reservation> completed = reservationRepository.getReservationByStstus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStstus("cancelled");

        int cantidadCompletadas = completed.size();
        int cantidadCanceladas = cancelled.size();

        return new CompletedAndCanceled(cantidadCompletadas, cantidadCanceladas);
    }

    public List<TotalAndClient> getTopClientesReport() {
        return reservationRepository.getTopClients();
    }
}
