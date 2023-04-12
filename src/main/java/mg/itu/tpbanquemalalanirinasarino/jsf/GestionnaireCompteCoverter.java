/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import mg.itu.tpbanquemalalanirinasarino.ejb.GestionnaireCompte;
import mg.itu.tpbanquemalalanirinasarino.entities.CompteBancaire;

/**
 *
 * @author Sarino
 */
@FacesConverter(value = "compteBancaireConverter", managed = true)
public class GestionnaireCompteCoverter implements Converter<CompteBancaire> {

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    @Override
    public CompteBancaire getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.valueOf(value);
        return gestionnaireCompte.findById(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, CompteBancaire compte) {
        if (compte == null) {
            return "";
        }
        return compte.getId().toString();
    }
    
}
