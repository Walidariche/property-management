package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@Repository
@CrossOrigin("*")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {


}

