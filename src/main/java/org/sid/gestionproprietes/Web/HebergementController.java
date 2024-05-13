package org.sid.gestionproprietes.Web;

import org.sid.gestionproprietes.Entities.Hebergement;
import org.sid.gestionproprietes.Entities.Reservation;
import org.sid.gestionproprietes.Entities.Ville;
import org.sid.gestionproprietes.Repository.CategorieRepository;
import org.sid.gestionproprietes.Repository.HebergementRepository;
import org.sid.gestionproprietes.Repository.VilleRepository;
import org.sid.gestionproprietes.Service.HebergementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@RestController

public class HebergementController {

    @Autowired
    private HebergementService hebergementService;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private HebergementRepository hebergementRepository;

    @GetMapping("/hebergements")
    public List<Hebergement> getHebergements() {
       List<Hebergement> hebergements= hebergementService.listAllHebergement();

        return hebergements;
    }



    @DeleteMapping("/deleteHebergement/{id}")
    public void  deleteHebergement(@PathVariable Long id){
        hebergementService.deleteHebergement(id);
    }

    @PutMapping("/updateHebergement")
    public void updateHebergement(@RequestBody Hebergement hebergement){

        hebergementService.modifyHebergement(hebergement);
    }

    @PostMapping("/AddHebergement")
    public Hebergement addHebergement(@RequestBody Hebergement hebergement) {

        Hebergement hebergement1 = hebergementService.saveHebergement(hebergement);
        return hebergement1;
    }

    @GetMapping(value = "/ImageHebergement/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[]image(@PathVariable(name = "id") Long id) throws IOException {
         Hebergement hebergement=hebergementRepository.findById(id).get();
         String PhotoName=hebergement.getImage();
       File file=new File(System.getProperty("user.home")+"/hebergement/images/"+PhotoName);
       Path path=Paths.get(file.toURI());
       return Files.readAllBytes(path);

    }

   @PostMapping("/Reservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
       Reservation reservation1=hebergementService.saveRESERVATION(reservation);
      return reservation1;
   }

    @GetMapping("/AllReservations")
    public List<Reservation> getAllReservations() {

        List<Reservation> reservations= hebergementService.listAllReservation();
        return reservations;
    }

    @GetMapping("/villes")
    public List<Ville> getVilles() {

        List<Ville> villes=hebergementService.listAllVille();
        return villes;
    }


}
