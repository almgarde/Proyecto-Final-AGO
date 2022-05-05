package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad: Categorías técnicas. Tabla: TECHNICAL_CATEGORIES
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "TECHNICAL_CATEGORIES")
public class TechnicalCategory extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Categoría Tecnica */
	private Long idTechCat;

	/** Nombre */
	private String nameTechCat;
	
	/** Lista de servicios de investigación */
	private List<Facility> listFacilities;

	@Id
	@GeneratedValue(generator = "genSeqTechCat")
	@SequenceGenerator(name = "genSeqTechCat", sequenceName = "SEQ_TECHNICAL_CATEGORIES", allocationSize = 1)
	public Long getIdTechCat() {
		return idTechCat;
	}

	public void setIdTechCat(Long idTechCat) {
		this.idTechCat = idTechCat;
	}

	@Column(name = "NAME_TECH_CAT", nullable = false, unique = true)
	public String getNameTechCat() {
		return nameTechCat;
	}

	public void setNameTechCat(String nameTechCat) {
		this.nameTechCat = nameTechCat;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTechCat")
	public List<Facility> getListFacilities() {
		return listFacilities;
	}

	public void setListFacilities(List<Facility> listFacilities) {
		this.listFacilities = listFacilities;
	}
	
	

}
