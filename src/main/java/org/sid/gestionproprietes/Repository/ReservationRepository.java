package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Hebergement;
import org.sid.gestionproprietes.Entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Override
    Page<Reservation> findAll(Pageable pageable);
}
