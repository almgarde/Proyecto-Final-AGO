package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

import java.util.Date;

/**
 * DTO entidad: Tesis
 * 
 * @author agadelao
 *
 */
public class ThesisDto {

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

	public String getCoverPageThesis() {
		return coverPageThesis;
	}

	public void setCoverPageThesis(String coverPageThesis) {
		this.coverPageThesis = coverPageThesis;
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
