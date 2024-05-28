package org.sid.gestionproprietes.Service;
import jakarta.transaction.Transactional;
import org.sid.gestionproprietes.Entities.*;
import org.sid.gestionproprietes.Repository.*;
import org.sid.gestionproprietes.Web.HebergementController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@Transactional

public class hebergementServiceImpl implements HebergementService{

    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private HebergementRepository hebergementRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private JdbcClient jdbcClient;


    @Override
    public List<Hebergement> listAllHebergement() {

       List<Hebergement> hebergement =hebergementRepository.findAll();
        return hebergement;
    }



    @Override
    public void deleteHebergement(Long id) {

     hebergementRepository.deleteById(id);

    }

    @Override
    public Hebergement modifyHebergement(Hebergement hebergement) {
        Hebergement hebergement1 = hebergementRepository.save(hebergement);
        return hebergement1;
    }
    @Override
    public Hebergement saveHebergement(Hebergement hebergement) {
             hebergement.setImage(hebergement.getImage().replaceAll(" ","")+".jpg");


            hebergementRepository.save(hebergement);

             return hebergement;


    }



    @Override
     public List<Reservation> listAllReservation() {

       List<Reservation> reservationList =reservationRepository.findAll();
        return reservationList;

    }

    @Override
    public List<Ville> listAllVille() {

        List<Ville> villeList =villeRepository.findAll();

        return villeList;
    }

    @Override
    public List<Categorie> listAllCategorie() {

        List<Categorie> categorieList =categorieRepository.findAll();
        return categorieList;
    }


    @Override
    public Reservation saveRESERVATION( Reservation reservation) {

        Hebergement hebergement = hebergementRepository.getOne(reservation.getHebergement().getId());

       LocalDate debut = reservation.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       LocalDate fin = reservation.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       long days = ChronoUnit.DAYS.between(debut, fin);
       Double price= hebergement.getPrix() * days;
          reservation.setReserved(true);
        reservation.setDate_reservation(new Date());
           reservation.setMontant(price);




        reservationRepository.save(reservation);
        return reservation;

    }




}

