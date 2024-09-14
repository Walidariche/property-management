package org.sid.gestionproprietes.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.sid.gestionproprietes.Entities.*;
import org.sid.gestionproprietes.Entities.EnvoiEmail;
import org.sid.gestionproprietes.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EnvoiEmailRepository envoiEmailRepository;


    @Override
    public Page<Hebergement> listAllHebergement(int page, int size) {
       Pageable pageable=PageRequest.of(page, size);

        return   hebergementRepository.findAll(pageable);
    }



    @Override
    public void deleteHebergement(Long id) {

     hebergementRepository.deleteById(id);

    }

    @Override
    public Hebergement modifyHebergement(Hebergement hebergement) {
        Optional<Hebergement> existingHebergementOpt = hebergementRepository.findById(hebergement.getId());
        if (existingHebergementOpt.isPresent()) {
            Hebergement existingHebergement = existingHebergementOpt.get();


            if (hebergement.getNom() != null) existingHebergement.setNom(hebergement.getNom());
            if (hebergement.getImage() != null) existingHebergement.setImage(hebergement.getImage().replaceAll(" ","")+".jpg");
            if (hebergement.getDescription() != null) existingHebergement.setDescription(hebergement.getDescription());
            if (hebergement.getPrix() != null) existingHebergement.setPrix(hebergement.getPrix());
            if (hebergement.getCapacite() != null) existingHebergement.setCapacite(hebergement.getCapacite());
            if (hebergement.getChambre() != 0) existingHebergement.setChambre(hebergement.getChambre());
            if (hebergement.getSallesdebains() != 0) existingHebergement.setSallesdebains(hebergement.getSallesdebains());
            if (hebergement.getPlacesdestationnement() != 0) existingHebergement.setPlacesdestationnement(hebergement.getPlacesdestationnement());
            if (hebergement.getEtage() != 0) existingHebergement.setEtage(hebergement.getEtage());
            if (hebergement.getCategorie() != null) existingHebergement.setCategorie(hebergement.getCategorie());
            if (hebergement.getVille() != null) existingHebergement.setVille(hebergement.getVille());

            return hebergementRepository.save(existingHebergement);
        } else {
            throw new EntityNotFoundException("Hebergement not found with id " + hebergement.getId());
        }
    }
    @Override
    public Hebergement saveHebergement(Hebergement hebergement) {
             hebergement.setImage(hebergement.getImage().replaceAll(" ","")+".jpg");
            hebergementRepository.save(hebergement);
             return hebergement;
    }
    @Override
     public Page<Reservation> listAllReservation(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return  reservationRepository.findAll(pageable);
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
    public void saveEnvoiEmail(String Toemail,String subject ,String body) {
      EnvoiEmail envoiEmail=new EnvoiEmail();
      envoiEmail.setBody(body);
      envoiEmail.setSubject(subject);
      envoiEmail.setBody(body);

           envoiEmailRepository.save(envoiEmail);




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

