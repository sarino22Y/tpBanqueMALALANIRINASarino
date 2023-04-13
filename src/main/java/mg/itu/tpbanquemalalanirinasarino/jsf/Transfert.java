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
import java.io.Serializable;
import mg.itu.tpbanquemalalanirinasarino.ejb.GestionnaireCompte;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;

/**
 *
 * @author Sarino
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert implements Serializable {

    private Long source;
    private Long destination;
    private int montant;

    @EJB
    private GestionnaireCompte transfert;

    /**
     * Creates a new instance of TransfertArgent
     */
    public Transfert() {
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long destination) {
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
        CompteBancaire compteSource = this.transfert.findById(source);
        CompteBancaire compteDestination = this.transfert.findById(destination);
        if (compteSource == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        }
         if (compteDestination == null) {
             Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }
        if(erreur){
            return null;
        }
        this.transfert.transferer(compteSource, compteDestination, this.montant);
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes.xhtml?faces-redirect=true";
    }
}
