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
	private Long idFacilitie;

	/** Nombre */
	private String nameFacilitie;

	/** Categoría */
	private TechnicalCategory idTechCat;

	/** Imagen */
	private String imageFacilitie;

	/** Características */
	private String featuresFacilitie;

	@Id
	@GeneratedValue(generator = "genSeqFacilities")
	@SequenceGenerator(name = "genSeqFacilities", sequenceName = "SEQ_FACILITIES", allocationSize = 1)
	public Long getIdFacilitie() {
		return idFacilitie;
	}

	public void setIdFacilitie(Long idFacilitie) {
		this.idFacilitie = idFacilitie;
	}

	@Column(name = "NAME_FACILITIE", nullable = false)
	public String getNameFacilitie() {
		return nameFacilitie;
	}

	public void setNameFacilitie(String nameFacilitie) {
		this.nameFacilitie = nameFacilitie;
	}

	@ManyToOne
	@JoinColumn(name = "ID_TECH_CAT")
	public TechnicalCategory getIdTechCat() {
		return idTechCat;
	}

	public void setIdTechCat(TechnicalCategory idTechCat) {
		this.idTechCat = idTechCat;
	}

	@Column(name = "IMAGE_FACILITIE", nullable = false)
	public String getImageFacilitie() {
		return imageFacilitie;
	}

	public void setImageFacilitie(String imageFacilitie) {
		this.imageFacilitie = imageFacilitie;
	}

	@Column(name = "FEATURES_FACILITIE", nullable = true)
	public String getFeaturesFacilitie() {
		return featuresFacilitie;
	}

	public void setFeaturesFacilitie(String featuresFacilitie) {
		this.featuresFacilitie = featuresFacilitie;
	}

}
