package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Proyectos. Tabla: PROJECTS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PROJECTS")
public class Projects extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Project */
	private Long idProject;

	/** Título */
	private String titleProject;

	/** Imagen */
	private String imageProject;

	/** Descripción */
	private String descriptionProject;

	public Long getIdProject() {
		return idProject;
	}

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	public String getTitleProject() {
		return titleProject;
	}

	public void setTitleProject(String titleProject) {
		this.titleProject = titleProject;
	}

	public String getImageProject() {
		return imageProject;
	}

	public void setImageProject(String imageProject) {
		this.imageProject = imageProject;
	}

	public String getDescriptionProject() {
		return descriptionProject;
	}

	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}

}
