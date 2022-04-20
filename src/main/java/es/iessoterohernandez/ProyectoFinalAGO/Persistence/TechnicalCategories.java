package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Categorías técnicas. Tabla: TECHNICAL_CATEGORIES
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "TECHNICAL_CATEGORIES")
public class TechnicalCategories extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Categoría Tecnica */
	private Long idTechCat;

	/** Nombre */
	private String nameTechCat;

	public Long getIdTechCat() {
		return idTechCat;
	}

	public void setIdTechCat(Long idTechCat) {
		this.idTechCat = idTechCat;
	}

	public String getNameTechCat() {
		return nameTechCat;
	}

	public void setNameTechCat(String nameTechCat) {
		this.nameTechCat = nameTechCat;
	}

}
