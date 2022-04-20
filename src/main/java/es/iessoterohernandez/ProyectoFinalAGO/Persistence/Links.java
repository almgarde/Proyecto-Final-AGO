package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Links. Tabla: LINKS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "LINKS")
public class Links extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Link */
	private Long idLink;

	/** Título */
	private String titleLink;

	/** Imagen */
	private String imageLink;

	/** URL */
	private String urlLink;

	public Long getIdLink() {
		return idLink;
	}

	public void setIdLink(Long idLink) {
		this.idLink = idLink;
	}

	public String getTitleLink() {
		return titleLink;
	}

	public void setTitleLink(String titleLink) {
		this.titleLink = titleLink;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

}
