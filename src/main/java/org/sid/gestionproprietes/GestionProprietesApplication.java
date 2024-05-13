package org.sid.gestionproprietes;

import org.sid.gestionproprietes.Entities.*;
import org.sid.gestionproprietes.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionProprietesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionProprietesApplication.class, args);
    }
     @Autowired
      private VilleRepository villeRepository;
     @Autowired
      private HebergementRepository hebergementRepository;
     @Autowired
     private CategorieRepository categorieRepository;
     @Autowired
     private ClientRepository clientRepository;
     @Autowired
     private ReservationRepository reservationRepository;

    @Bean
    CommandLineRunner commandLineRunner(VilleRepository villeRepository,
                                        HebergementRepository hebergementRepository,
                                        CategorieRepository categorieRepository,
                                         ClientRepository clientRepository,
                                        ReservationRepository reservationRepository ) {

        return args -> {

         Map<String,String> adressesVilles = new HashMap<>();
         adressesVilles.put("Rabat", "Hay laymoun");
         adressesVilles.put("Casablanca", "Hay al qods");
         adressesVilles.put("Marrakech", "gueliz");

         adressesVilles.forEach((nomville,adresse)->{

             Ville ville=new Ville();
             ville.setNomVille(nomville);
             ville.setAdresse(adresse);
             villeRepository.save(ville);

         });
             Stream.of("Auberge","Appartement","Villa","Riad").forEach(cat->{
                Categorie categorie=new Categorie();
                categorie.setType(cat);
                categorieRepository.save(categorie);
             });


              Map<String,String> descriptionheberhement = new HashMap<>();
                descriptionheberhement.put("Villa Oasis", "rofitez d'un séjour luxueux dans notre Villa Oasi");
                 descriptionheberhement.put("Chalet Montagne", " Plongez dans l'atmosphère chaleureuse et rustique de notre Chalet Montagne");
                 descriptionheberhement.put("Appartement Vue Mer", "étendez-vous et admirez les vagues depuis notre Appartement Vue Mer");
                 descriptionheberhement.put("Cabane dans les Arbres", "Vivez une expérience unique en séjournant dans notre Cabane dans les Arbres");
            Random random = new Random();
                             double[] prices=new double[]{200,300,400,500,600,700,800,900,1000};
                             double[] capacite=new double[]{100,110,120,130,140,150,160,170};
                          List<Ville> villes = villeRepository.findAll();
                          List<Categorie > catgories= categorieRepository.findAll();

                          descriptionheberhement.forEach((nomHeber, description) -> {

                              Ville ville=villes.get(random.nextInt(villes.size()));
                              Categorie categorie=catgories.get(random.nextInt(catgories.size()));

                            Hebergement hebergement=new Hebergement();
                            hebergement.setNom(nomHeber);
                            hebergement.setDescription(description);
                           hebergement.setCategorie(categorie);
                            hebergement.setVille(ville);
                          hebergement.setPrix(prices[random.nextInt(prices.length)]);
                          hebergement.setCapacite(capacite[random.nextInt(capacite.length)]);
                          hebergement.setImage(nomHeber.replace(" ","")+".jpg");
                            hebergementRepository.save(hebergement);



             });

            List<Client> clients =new ArrayList<>();
            clients.add(new Client("ahmed","ahmes@gmail.COM","0654546365"));
            clients.add(new Client("anas","anas@gmail.COM","0676589542"));
            clients.add(new Client("mustapha","mustapha@gmail.COM","0678543198"));
            clients.add(new Client("ibrahim","ibrahim@gmail.COM","0694825792"));
               clientRepository.saveAll(clients);



              // List<Hebergement> hebergements=hebergementRepository.findAll();
               hebergementRepository.findAll().forEach(hebergements->{


             Date dateres=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateres);
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            Date dateDebut = calendar.getTime();
            calendar.setTime(dateDebut);
            calendar.add(Calendar.DAY_OF_MONTH, 10);

            Date dateFIN = calendar.getTime();
            Reservation reservation1=new Reservation();
            reservation1.setClient(clients.get(random.nextInt(clients.size())));
            //reservation1.setHebergement(hebergements.get(random.nextInt(hebergements.size())));
                   reservation1.setHebergement(hebergements);
           double prix=   reservation1.getHebergement().getPrix();
             reservation1.setDate_reservation(dateres);
              reservation1.setDate_debut(dateDebut);
              reservation1.setDate_fin(dateFIN);
            long diffInMillies = Math.abs(dateFIN.getTime() - dateDebut.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            reservation1.setReserved(true);
              reservation1.setMontant(prix *diffInDays);
            reservationRepository.save(reservation1);




               });
        };


}
}
