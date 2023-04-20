/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;
import mg.itu.tpbanquemalalanirinasarino.exception.ConcurrentAccessException;

/**
 *
 * @author Sarino
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "sarino", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.getAll", CompteBancaire.class);
        List<CompteBancaire> liste = query.getResultList();
        return liste;
    }

    public CompteBancaire findById(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public int nbComptes() {
        TypedQuery<Long> query = em.createNamedQuery("CompteBancaire.getCount", Long.class);
        Long nb = query.getSingleResult();
        return nb.intValue();
    }

    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    /**
     * Dépôt d'argent sur un compte bancaire.V ersion qui tient compte de la
     * concurrence.
     *
     * @param compteBancaire
     * @param montant
     */
    public void deposer(CompteBancaire compte, int montant) {
        int solde = compte.getSolde();
        boolean concurrence = false;
        compte = em.find(CompteBancaire.class, compte.getId(),
                LockModeType.PESSIMISTIC_WRITE);
        if (solde != compte.getSolde()) {
            concurrence = true;
        }
        compte.deposer(montant);
        update(compte);
        if (concurrence) {
            throw new ConcurrentAccessException("Retrait effectué mais le solde a été modifié concurremment");
        }
    }

    /**
     * Retrait d'argent sur un compte bancaire. Version qui tient compte de la
     * concurrence.
     *
     * @param compteBancaire
     * @param montant
     */
    public void retirer(CompteBancaire compte, int montant) {
        int solde = compte.getSolde();
        boolean concurrence = false;
        compte = em.find(CompteBancaire.class, compte.getId(),
                LockModeType.PESSIMISTIC_WRITE);
        if (solde != compte.getSolde()) {
            concurrence = true;
        }
        compte.retirer(montant);
        update(compte);
        if (concurrence) {
            throw new ConcurrentAccessException("Retrait effectué mais le solde a été modifié concurremment");
        }
    }

    // Supprimer un compte
    public void supprimer(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
}
