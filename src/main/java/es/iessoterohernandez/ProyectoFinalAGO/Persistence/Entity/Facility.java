package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad: Servicios de investigación. Tabla: FACILITIES
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "FACILITIES")
public class Facility extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Servicio de investigación */
	private Long idFacility;

	/** Nombre */
	private String nameFacility;

	/** Categoría técnica */
	private TechnicalCategory idTechCat;

	/** Imagen */
	private String photoFacility;

	/** Características */
	private String featuresFacility;

	@Id
	@GeneratedValue(generator = "genSeqFacilities")
	@SequenceGenerator(name = "genSeqFacilities", sequenceName = "SEQ_FACILITIES", allocationSize = 1)
	public Long getIdFacility() {
		return idFacility;
	}

	public void setIdFacility(Long idFacility) {
		this.idFacility = idFacility;
	}

	@Column(name = "NAME_FACILITY", nullable = false)
	public String getNameFacility() {
		return nameFacility;
	}

	public void setNameFacility(String nameFacility) {
		this.nameFacility = nameFacility;
	}

	@ManyToOne
	@JoinColumn(name = "ID_TECH_CAT")
	public TechnicalCategory getIdTechCat() {
		return idTechCat;
	}

	public void setIdTechCat(TechnicalCategory idTechCat) {
		this.idTechCat = idTechCat;
	}

	@Column(name = "IMAGE_FACILITY", nullable = false)
	public String getPhotoFacility() {
		return photoFacility;
	}

	public void setPhotoFacility(String photoFacility) {
		this.photoFacility = photoFacility;
	}

	@Column(name = "FEATURES_FACILITY", nullable = true)
	public String getFeaturesFacility() {
		return featuresFacility;
	}

	public void setFeaturesFacility(String featuresFacility) {
		this.featuresFacility = featuresFacility;
	}

}
