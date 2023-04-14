/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanquemalalanirinasarino.ejb.GestionnaireCompte;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;

/**
 *
 * @author Sarino
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> compteBancaireListes;

    @EJB
    private GestionnaireCompte gCompte;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    /*Retourner la liste des comptes bancaire. */
    public List<CompteBancaire> getAllComptes() {
        if (compteBancaireListes == null) {
            compteBancaireListes = gCompte.getAllComptes();
        }
        return (List<CompteBancaire>) compteBancaireListes;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gCompte.supprimer(compteBancaire);
        Util.addFlashInfoMessage("Suppression de " + compteBancaire.getNom() + " avec succès");
        return "listeComptes?faces-redirect=true";
    }
}
