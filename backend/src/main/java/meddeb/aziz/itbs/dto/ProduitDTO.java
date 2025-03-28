package meddeb.aziz.itbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {

    private long id ;

    private String nom ;

    private String categorie;

    private double prix;

    private String fournisseur;

    private int seuilMin;

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getPrix() {
        return prix;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public int getSeuilMin() {
        return seuilMin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setSeuilMin(int seuilMin) {
        this.seuilMin = seuilMin;
    }
}
