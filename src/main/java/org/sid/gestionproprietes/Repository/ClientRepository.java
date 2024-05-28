package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@Repository
@CrossOrigin("*")
public interface ClientRepository extends JpaRepository<Client, Long> {

}
