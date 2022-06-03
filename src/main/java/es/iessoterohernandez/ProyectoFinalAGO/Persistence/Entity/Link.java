package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad: Links. Tabla: LINKS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "LINKS")
public class Link extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Link */
	private Long idLink;

	/** Título */
	private String titleLink;

	/** Imagen */
	private String imageLink;

	/** URL */
	private String urlLink;

	@Id
	@GeneratedValue(generator = "genSeqLinks")
	@SequenceGenerator(name = "genSeqLinks", sequenceName = "SEQ_LINKS", allocationSize = 1)
	public Long getIdLink() {
		return idLink;
	}

	public void setIdLink(Long idLink) {
		this.idLink = idLink;
	}

	@Column(name = "TITLE_LINK", nullable = false, unique = true)
	public String getTitleLink() {
		return titleLink;
	}

	public void setTitleLink(String titleLink) {
		this.titleLink = titleLink;
	}

	@Column(name = "IMAGE_LINK", nullable = false)
	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Column(name = "URL_LINK", nullable = false)
	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

}
