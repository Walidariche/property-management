package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {


}
