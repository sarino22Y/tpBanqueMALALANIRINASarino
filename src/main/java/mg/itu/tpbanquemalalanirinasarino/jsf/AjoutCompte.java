/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanquemalalanirinasarino.ejb.GestionnaireCompte;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;

/**
 *
 * @author Sarino
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    private Long id;
    private String nom;
    @PositiveOrZero
    private int solde;

    @EJB
    private GestionnaireCompte gCompte;

    private CompteBancaire compteBancaire;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
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

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    // Créer un nouveau compte.
    public String action() {
        compteBancaire = new CompteBancaire(nom, solde);
        gCompte.creerCompte(compteBancaire);
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Compte créé avec succès");
        return "listeComptes.xhtml?faces-redirect=true";
    }

    public void loadCompte() {
        this.compteBancaire = gCompte.findById(id);
    }
}
