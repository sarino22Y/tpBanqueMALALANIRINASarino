/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import mg.itu.tpbanquemalalanirinasarino.ejb.GestionnaireCompte;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;

/**
 *
 * @author Ibonia
 */
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    private long id;
    private CompteBancaire compte;

    @EJB
    private GestionnaireCompte gCompte;

    /**
     * Creates a new instance of ModificationBean
     */
    public Modification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public void loadCompte() {
        this.compte = gCompte.findById(id);
    }

    public String update() {
        gCompte.update(compte);
        Util.addFlashInfoMessage("Modifications enregistrées");
        return "modifierNomCompte?id=" + id + "&amp;faces-redirect=true";
    }

}
