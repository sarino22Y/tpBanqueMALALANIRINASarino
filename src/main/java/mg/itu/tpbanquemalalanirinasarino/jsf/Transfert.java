/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import mg.itu.tpbanquemalalanirinasarino.ejb.TransfertArgent;
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
    private TransfertArgent transfert;

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
        transfert.transferer(source, destination, montant);
        
        return "listeComptes.xhtml?faces-redirect=true";
    }
}
