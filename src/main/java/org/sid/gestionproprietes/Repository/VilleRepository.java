package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Hebergement;
import org.sid.gestionproprietes.Entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("*")
public interface VilleRepository extends JpaRepository<Ville, Long> {





}
