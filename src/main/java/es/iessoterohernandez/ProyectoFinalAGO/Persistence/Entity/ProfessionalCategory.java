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
 * Entidad: Categorías profesionales. Tabla: PROFESSIONAL_CATEGORIES
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PROFESSIONAL_CATEGORIES")
public class ProfessionalCategory extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Categoría Profesional */
	private Long idProCat;

	/** Nombre */
	private String nameProCat;
	
	/** Lista de miembros */
	private List<Member> listMembers;
	
	

	@Id
	@GeneratedValue(generator = "genSeqProCat")
	@SequenceGenerator(name = "genSeqproCat", sequenceName = "SEQ_PROFESSIONAL_CATEGORIES", allocationSize = 1)
	public Long getIdProCat() {
		return idProCat;
	}

	public void setIdProCat(Long idProCat) {
		this.idProCat = idProCat;
	}

	@Column(name = "NAME_PRO_CAT", nullable = false, unique = true)
	public String getNameProCat() {
		return nameProCat;
	}

	public void setNameProCat(String nameProCat) {
		this.nameProCat = nameProCat;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProCat")
	public List<Member> getListMembers() {
		return listMembers;
	}

	public void setListMembers(List<Member> listMembers) {
		this.listMembers = listMembers;
	}
	
	

}
