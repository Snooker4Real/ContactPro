package fr.snooker4real.contactpro.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private Long id;
    private String nom;
    private String prenom;
    private String societe;
    private String adresse;
    private String telephone;
    private String email;
    private String secteur;
    private int favori;

    public Contact() {
    }

    public Contact(String nom, String prenom, String societe, String adresse, String telephone, String email, String secteur, int favori) {
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.secteur = secteur;
        this.favori = favori;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public int getFavori() {
        return favori;
    }

    public void setFavori(int favori) {
        this.favori = favori;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", societe='" + societe + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", secteur='" + secteur + '\'' +
                ", favori=" + favori +
                '}';
    }
}
