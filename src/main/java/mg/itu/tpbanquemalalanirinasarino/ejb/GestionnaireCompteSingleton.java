/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;

/**
 *
 * @author Sarino
 */
@Singleton
@Startup
public class GestionnaireCompteSingleton {

    @EJB
    private GestionnaireCompte gCompte;

    @PostConstruct
    public void init() {
        createAccounts();
    }

    public void createAccounts() {
        if (gCompte.nbComptes() <= 0) {
            CompteBancaire compte1 = new CompteBancaire("John Lennon", 150000);
            CompteBancaire compte2 = new CompteBancaire("Paul McCartney", 950000);
            CompteBancaire compte3 = new CompteBancaire("Ringo Starr", 20000);
            CompteBancaire compte4 = new CompteBancaire("Georges Harrisson", 100000);

            gCompte.creerCompte(compte1);
            gCompte.creerCompte(compte2);
            gCompte.creerCompte(compte3);
            gCompte.creerCompte(compte4);
        }
    }
}
