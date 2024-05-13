package org.sid.gestionproprietes.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hebergement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String image;
    private String description;
    private Double prix;
    private Double capacite;

    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private Ville ville;

    @OneToMany(mappedBy = "hebergement")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Reservation>reservations;
}
