package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.EnvoiEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface EnvoiEmailRepository extends JpaRepository<EnvoiEmail,Long> {

}
