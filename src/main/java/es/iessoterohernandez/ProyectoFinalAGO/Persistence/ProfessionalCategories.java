package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Categorías profesionales. Tabla: PROFESSIONAL_CATEGORIES
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PROFESSIONAL_CATEGORIES")
public class ProfessionalCategories extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID de Categorías Profesionales */
	private String idProCat;

	/** Fecha del registro */
	private Date nameProCat;

	public String getIdProCat() {
		return idProCat;
	}

	public void setIdProCat(String idProCat) {
		this.idProCat = idProCat;
	}

	public Date getNameProCat() {
		return nameProCat;
	}

	public void setNameProCat(Date nameProCat) {
		this.nameProCat = nameProCat;
	}
	
	

}
