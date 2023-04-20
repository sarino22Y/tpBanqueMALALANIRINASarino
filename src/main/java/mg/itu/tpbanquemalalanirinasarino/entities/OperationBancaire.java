/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/**
 *
 * @author Sarino
 */
@Entity
public class OperationBancaire {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private int montant;

    private LocalDateTime dateOperation;

    public OperationBancaire() {
    }

    public OperationBancaire(String description, int montant) {
        this.description = description;
        this.montant = montant;
        this.dateOperation = LocalDateTime.now();
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    @Override
    public String toString() {
        return "mg.itu.tpbanquemalalanirinasarino.entities.OperationBancaire[ id=" + id + " ]";
    }

    @Override
    public boolean equals(Object obj) {
         // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(obj instanceof OperationBancaire)) {
            return false;
        }
        OperationBancaire other = (OperationBancaire) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

}
