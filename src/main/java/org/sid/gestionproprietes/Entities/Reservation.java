package org.sid.gestionproprietes.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_debut;
    private Date date_fin;
    private Date date_reservation;
    private Double montant;
    private Boolean reserved;
    private String nom;
    private String email;
    private String Telephone;

    @ManyToOne
    private Hebergement hebergement;

    @ManyToOne
    private Client client;



}
