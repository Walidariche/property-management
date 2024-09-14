package org.sid.gestionproprietes.Web;

import org.sid.gestionproprietes.Entities.*;
import org.sid.gestionproprietes.Repository.CategorieRepository;
import org.sid.gestionproprietes.Repository.EnvoiEmailRepository;
import org.sid.gestionproprietes.Repository.HebergementRepository;
import org.sid.gestionproprietes.Repository.VilleRepository;
import org.sid.gestionproprietes.Service.HebergementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin("*")
public class HebergementController {

    @Autowired
    private HebergementService hebergementService;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private HebergementRepository hebergementRepository;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/hebergements")
    public Page<Hebergement> getHebergements(@RequestParam int page,@RequestParam int size) {
       Page<Hebergement> hebergements= hebergementService.listAllHebergement(page, size);

        return hebergements;
    }



    @DeleteMapping("/deleteHebergement/{id}")
    public void  deleteHebergement(@PathVariable Long id){
        hebergementService.deleteHebergement(id);
    }

    @GetMapping("/hebergement/{id}")
    public Hebergement getHebergementbyid (@PathVariable Long id){
         Hebergement hebergement=hebergementRepository.findById(id).get();
         return hebergement;
    }

    @PutMapping("/updateHebergement")
    public void updateHebergement( @RequestBody Hebergement hebergement){

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
    public Page<Reservation> getAllReservations(@RequestParam int page, @RequestParam int size) {
        Page<Reservation> reservations= hebergementService.listAllReservation(page,size);
        return reservations;
    }

    @GetMapping("/villes")
    public List<Ville> getVilles() {

        List<Ville> villes=hebergementService.listAllVille();
        return villes;
    }

    @GetMapping("/categories")
    public List<Categorie> getCategories() {

        List<Categorie> Categories =hebergementService.listAllCategorie();
        return Categories;
    }

  /*  @GetMapping("/EnvoiEmail")
    public void sendEmail(){
   EnvoiEmailRepository envoiEmailRepository;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("walid.aariche2001@gmail.com");
        message.setTo("walid.aariche2001@gmail.com");
        message.setText("body");
        message.setSubject("subject");

        mailSender.send(message);

    }*/
  @PostMapping("/EnvoiEmail")
  public void sendEmail(@RequestBody EnvoiEmail envoiEmail) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("walid.aariche2001@gmail.com");
      message.setTo(envoiEmail.getToemail());
      message.setText(envoiEmail.getBody());
      message.setSubject(envoiEmail.getSubject());

      mailSender.send(message);
  }

}




