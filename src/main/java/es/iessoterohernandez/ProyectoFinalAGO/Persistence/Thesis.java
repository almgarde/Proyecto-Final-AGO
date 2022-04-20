package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Tesis Doctorales. Tabla: THESIS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "THESIS")
public class Thesis extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Tesis */
	private Long idThesis;

	/** Doctorando */
	private String doctorThesis;

	/** Título */
	private String titleThesis;

	/** Fecha de defensa */
	private Date dateDefenseThesis;

	/** Director */
	private String directorThesis;

	/** Co-director */
	private String coDirectorThesis;

	/** URL */
	private String urlThesis;

	public Long getIdThesis() {
		return idThesis;
	}

	public void setIdThesis(Long idThesis) {
		this.idThesis = idThesis;
	}

	public String getDoctorThesis() {
		return doctorThesis;
	}

	public void setDoctorThesis(String doctorThesis) {
		this.doctorThesis = doctorThesis;
	}

	public String getTitleThesis() {
		return titleThesis;
	}

	public void setTitleThesis(String titleThesis) {
		this.titleThesis = titleThesis;
	}

	public Date getDateDefenseThesis() {
		return dateDefenseThesis;
	}

	public void setDateDefenseThesis(Date dateDefenseThesis) {
		this.dateDefenseThesis = dateDefenseThesis;
	}

	public String getDirectorThesis() {
		return directorThesis;
	}

	public void setDirectorThesis(String directorThesis) {
		this.directorThesis = directorThesis;
	}

	public String getCoDirectorThesis() {
		return coDirectorThesis;
	}

	public void setCoDirectorThesis(String coDirectorThesis) {
		this.coDirectorThesis = coDirectorThesis;
	}

	public String getUrlThesis() {
		return urlThesis;
	}

	public void setUrlThesis(String urlThesis) {
		this.urlThesis = urlThesis;
	}

}
