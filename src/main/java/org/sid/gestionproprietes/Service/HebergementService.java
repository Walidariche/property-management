package org.sid.gestionproprietes.Service;

import org.sid.gestionproprietes.Entities.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


public interface HebergementService {

    public Page<Hebergement> listAllHebergement(int page,int size);

    public void deleteHebergement(Long id);

    public Hebergement modifyHebergement(Hebergement hebergement);

    public Hebergement saveHebergement(Hebergement hebergement);

    public Reservation saveRESERVATION(Reservation reservation);

    public Page<Reservation> listAllReservation(int page, int size);

    public List<Ville> listAllVille();
    public List<Categorie> listAllCategorie();

    public void saveEnvoiEmail(String Toemail,String subject ,String body);


}