package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Hebergement;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface HebergementRepository extends JpaRepository<Hebergement, Long> {


}

