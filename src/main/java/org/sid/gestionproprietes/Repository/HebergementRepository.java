package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Hebergement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("*")
public interface HebergementRepository extends JpaRepository<Hebergement, Long> {
    @Override
    Page<Hebergement> findAll(Pageable pageable);
}

