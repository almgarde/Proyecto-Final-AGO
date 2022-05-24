package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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

	/** Portada */
	private String coverPageThesis;

	/** Fecha de defensa */
	private Date dateDefenseThesis;

	/** Director */
	private String directorThesis;

	/** Co-director */
	private String coDirectorThesis;

	/** URL */
	private String urlThesis;

	@Id
	@GeneratedValue(generator = "genSeqThesis")
	@SequenceGenerator(name = "genSeqThesis", sequenceName = "SEQ_THESIS", allocationSize = 1)
	public Long getIdThesis() {
		return idThesis;
	}

	public void setIdThesis(Long idThesis) {
		this.idThesis = idThesis;
	}

	@Column(name = "DOCTOR_THESIS", nullable = false, unique = true)
	public String getDoctorThesis() {
		return doctorThesis;
	}

	public void setDoctorThesis(String doctorThesis) {
		this.doctorThesis = doctorThesis;
	}
	@Column(name = "TITLE_THESIS", nullable = false)
	public String getTitleThesis() {
		return titleThesis;
	}

	public void setTitleThesis(String titleThesis) {
		this.titleThesis = titleThesis;
	}
	@Column(name = "COVER_PAGE_THESIS", nullable = false)
	public String getCoverPageThesis() {
		return coverPageThesis;
	}

	public void setCoverPageThesis(String coverPageThesis) {
		this.coverPageThesis = coverPageThesis;
	}
	@Column(name = "DATE_DEFENSE_THESIS", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")

	public Date getDateDefenseThesis() {
		return dateDefenseThesis;
	}

	public void setDateDefenseThesis(Date dateDefenseThesis) {
		this.dateDefenseThesis = dateDefenseThesis;
	}
	@Column(name = "DIRECTOR_THESIS", nullable = false)
	public String getDirectorThesis() {
		return directorThesis;
	}

	public void setDirectorThesis(String directorThesis) {
		this.directorThesis = directorThesis;
	}
	@Column(name = "CODIRECTOR_THESIS", nullable = true)
	public String getCoDirectorThesis() {
		return coDirectorThesis;
	}

	public void setCoDirectorThesis(String coDirectorThesis) {
		this.coDirectorThesis = coDirectorThesis;
	}
	@Column(name = "URL_THESIS", nullable = true)
	public String getUrlThesis() {
		return urlThesis;
	}

	public void setUrlThesis(String urlThesis) {
		this.urlThesis = urlThesis;
	}

}
