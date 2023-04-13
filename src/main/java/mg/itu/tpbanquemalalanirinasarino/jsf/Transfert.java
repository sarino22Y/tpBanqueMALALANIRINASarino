/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mg.itu.tpbanquemalalanirinasarino.ejb.GestionnaireCompte;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;

/**
 *
 * @author Sarino
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert {

    private CompteBancaire source;
    private CompteBancaire destination;
    private int montant;

    @EJB
    private GestionnaireCompte transfert;

    /**
     * Creates a new instance of TransfertArgent
     */
    public Transfert() {
    }

    public CompteBancaire getSource() {
        return source;
    }

    public void setSource(CompteBancaire source) {
        this.source = source;
    }

    public CompteBancaire getDestination() {
        return destination;
    }

    public void setDestination(CompteBancaire destination) {
        this.destination = destination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String update() {
        boolean erreur = false;
        if (source == null || destination == null) {
            FacesMessage message
                    = new FacesMessage("Le destinataire ou l'expediteur n'existe pas");
            FacesContext.getCurrentInstance().addMessage("source_destination_null", message);
            erreur = true;
        }
        if (source.getSolde() < montant) {
            FacesMessage message
                    = new FacesMessage("Solde insuffisant");
            FacesContext.getCurrentInstance().addMessage("source_insuffisant", message);
            erreur = true;
        }

        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        transfert.transferer(source, destination, montant);
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes.xhtml?faces-redirect=true";
    }
}
