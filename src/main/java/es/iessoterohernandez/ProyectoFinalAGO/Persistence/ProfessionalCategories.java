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

	/** ID de Categoría Profesional */
	private Long idProCat;

	/** Nombre */
	private String nameProCat;

	public Long getIdProCat() {
		return idProCat;
	}

	public void setIdProCat(Long idProCat) {
		this.idProCat = idProCat;
	}

	public String getNameProCat() {
		return nameProCat;
	}

	public void setNameProCat(String nameProCat) {
		this.nameProCat = nameProCat;
	}

}
