package org.sid.gestionproprietes.Service;

import org.sid.gestionproprietes.Entities.Categorie;
import org.sid.gestionproprietes.Entities.Hebergement;
import org.sid.gestionproprietes.Entities.Reservation;
import org.sid.gestionproprietes.Entities.Ville;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


public interface HebergementService {

    public List<Hebergement> listAllHebergement();

    public void deleteHebergement(Long id);

    public Hebergement modifyHebergement(Hebergement hebergement);

    public Hebergement saveHebergement(Hebergement hebergement);

    public Reservation saveRESERVATION(Reservation reservation);

    public Page<Reservation> listAllReservation(int page, int size);

    public List<Ville> listAllVille();
    public List<Categorie> listAllCategorie();


}