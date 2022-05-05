package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad: Proyectos. Tabla: PROJECTS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PROJECTS")
public class Project extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Project */
	private Long idProject;

	/** Título */
	private String titleProject;

	/** Imagen */
	private String imageProject;

	/** Descripción */
	private String descriptionProject;

	@Id
	@GeneratedValue(generator = "genSeqProjects")
	@SequenceGenerator(name = "genSeqProjects", sequenceName = "SEQ_PROJECTS", allocationSize = 1)
	public Long getIdProject() {
		return idProject;
	}

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	@Column(name = "TITLE_PROJECT", nullable = false, unique = true)
	public String getTitleProject() {
		return titleProject;
	}

	public void setTitleProject(String titleProject) {
		this.titleProject = titleProject;
	}

	@Column(name = "IMAGE_PROJECT", nullable = false)
	public String getImageProject() {
		return imageProject;
	}

	public void setImageProject(String imageProject) {
		this.imageProject = imageProject;
	}

	@Column(name = "DESCRIPTION_PROJECT", nullable = false)
	public String getDescriptionProject() {
		return descriptionProject;
	}

	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}

}
