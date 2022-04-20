package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Servicios de investigación. Tabla: FACILITIES
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "FACILITIES")
public class Facilities extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Servicio de investigación */
	private Long idFacilitie;

	/** Nombre */
	private String nameFacilitie;

	/** Categoría */
	private String catTechFacilitie;

	/** Imagen */
	private String imageFacilitie;

	/** Características */
	private String featuresFacilitie;

	public Long getIdFacilitie() {
		return idFacilitie;
	}

	public void setIdFacilitie(Long idFacilitie) {
		this.idFacilitie = idFacilitie;
	}

	public String getNameFacilitie() {
		return nameFacilitie;
	}

	public void setNameFacilitie(String nameFacilitie) {
		this.nameFacilitie = nameFacilitie;
	}

	public String getCatTechFacilitie() {
		return catTechFacilitie;
	}

	public void setCatTechFacilitie(String catTechFacilitie) {
		this.catTechFacilitie = catTechFacilitie;
	}

	public String getImageFacilitie() {
		return imageFacilitie;
	}

	public void setImageFacilitie(String imageFacilitie) {
		this.imageFacilitie = imageFacilitie;
	}

	public String getFeaturesFacilitie() {
		return featuresFacilitie;
	}

	public void setFeaturesFacilitie(String featuresFacilitie) {
		this.featuresFacilitie = featuresFacilitie;
	}

}
