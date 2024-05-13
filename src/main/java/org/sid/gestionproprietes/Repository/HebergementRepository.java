package org.sid.gestionproprietes.Repository;

import org.sid.gestionproprietes.Entities.Hebergement;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HebergementRepository extends JpaRepository<Hebergement, Long> {

         @Query(value = "select * from Hebergement where ville LIKE %?1%", nativeQuery = true)
       List<Hebergement> findAllByVille(String ville);


}

